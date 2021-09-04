<template>
  <div class="home">
    <v-row>
      <v-col cols="12">
        <div v-if="certifications !== undefined && certifications.length > 0">
          <v-row>
            <v-col cols="12">
              <v-layout align-center justify-center>
                <h1
                  class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200 mt-3"
                >
                  <h1 class="text-center">Available Certifications</h1>
                </h1>
              </v-layout>
            </v-col>
          </v-row>
          <v-flex justify-center>
            <v-btn
              @click.stop="newCertificationDialog = true"
              color="primary"
              class="my-3"
              >New Certification
            </v-btn>
          </v-flex>
          <v-dialog
            v-model="newCertificationDialog"
            persistent
            max-width="600px"
          >
            <v-card>
              <v-card-title>
                New Certification Request
              </v-card-title>
              <v-card-text>
                <v-form ref="newCertificationForm" v-model="valid">
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="Name"
                        :rules="nameRules"
                        v-model="certificationName"
                        prepend-icon="mdi-certificate-outline"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" md="6" xs="12" class="py-0">
                      <v-text-field
                        label="Price"
                        v-model="certificationPrice"
                        prepend-icon="mdi-cash-multiple"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="6" xs="12" class="py-0">
                      <v-text-field
                        label="Currency"
                        :rules="currencyRules"
                        v-model="certificationCurrency"
                        prepend-icon="mdi-currency-eur"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="Url"
                        :rules="urlRules"
                        v-model="certificationUrl"
                        prepend-icon="mdi-web"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn @click="closeNewCertificationDialog" color="danger"
                  >Close</v-btn
                >
                <v-btn
                  :disabled="!valid"
                  @click="sendNewCertificationDialog"
                  color="success"
                  >Send</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-row>
            <v-col>
              <v-layout d-flex flex-wrap justify-space-around>
                <router-link
                  v-for="certification in certifications"
                  :key="certification.id"
                  :to="'/certificationDetail/' + certification.id"
                  tag="div"
                >
                  <CertificationItem
                    style="cursor: pointer"
                    :certification="certification"
                    ref="Home"
                  />
                </router-link>
              </v-layout>
            </v-col>
          </v-row>
        </div>
        <div v-else style="height:100wh">
          <h3>No certifications found</h3>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Vue from "vue";
import CertificationItem from "@/components/CertificationItem.vue";
import { mapGetters, mapMutations } from "vuex";
import axios from "axios";

export default Vue.extend({
  name: "Home",
  data() {
    return {
      valid: true,
      newCertificationDialog: false,
      certificationName: "",
      certificationPrice: 0,
      certificationCurrency: "",
      certificationUrl: "",
      nameRules: [v => !!v || "Name is required!"],
      currencyRules: [v => !!v || "Currency is required!"],
      urlRules: [v => !!v || "Url is required!"]
    };
  },
  components: {
    CertificationItem
  },
  computed: {
    ...mapGetters(["certifications"])
  },
  async mounted() {
    const token = JSON.parse(localStorage.getItem("token"));
    const { data } = await axios.get("http://localhost:8080/certifications", {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token
      }
    });
    this.certificationsMutation(data._embedded.certifications);
  },
  methods: {
    ...mapMutations(["certificationsMutation"]),
    closeNewCertificationDialog() {
      this.newCertificationDialog = false;
      this.certificationName = "";
      this.certificationPrice = 0;
      this.certificationCurrency = "";
      this.certificationUrl = "";
    },
    sendNewCertificationDialog() {
      this.$store
        .dispatch("createCertificationRequest", {
          name: this.certificationName,
          currency: this.certificationCurrency,
          price: this.certificationPrice,
          url: this.certificationUrl
        })
        .then(() => this.getCertificates());
      this.closeNewCertificationDialog();
    },
    async getCertificates() {
      let temp = [];
      const token = JSON.parse(localStorage.getItem("token"));
      temp = await axios.get("http://localhost:8080/certifications", {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token
        }
      });
      this.certificationsMutation(temp.data._embedded.certifications);
    }
  }
});
</script>
