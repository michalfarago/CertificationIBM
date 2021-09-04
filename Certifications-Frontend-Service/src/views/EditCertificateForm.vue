<template>
  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <v-flex md6 sm8 xs12>
        <v-row>
          <v-col cols="12">
            <v-layout align-center justify-center>
              <h1
                class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200 mb-5"
              >
                Edit Certification
              </h1>
            </v-layout>
            <v-card style="border-radius:25px;">
              <div
                class=" text-white flex items-center absolute py-4 px-4 shadow-xl bg-green-500 left-4 -top-6"
                style="border-radius:50%"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="20px"
                  height="20px"
                  fill="currentColor"
                  viewBox="0 0 15 15"
                >
                  <path
                    d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"
                  />
                  <path
                    fill-rule="evenodd"
                    d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"
                  />
                </svg>
              </div>
              <v-card-text class="mt-5">
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
                  <v-row>
                    <v-col cols="12">
                      Add / Remove Skills
                    </v-col>
                  </v-row>
                  <v-row class="my-0">
                    <v-virtual-scroll
                      :bench="benched"
                      :items="skills"
                      height="180"
                      item-height="40"
                    >
                      <template v-slot:default="{ item }">
                        <v-list-item :key="item">
                          <v-checkbox
                            v-model="certificationSkillList"
                            :label="item.name"
                            :value="item.id"
                          ></v-checkbox>
                        </v-list-item>
                      </template>
                      <v-divider></v-divider>
                    </v-virtual-scroll>
                  </v-row>
                </v-form>
              </v-card-text>
              <v-card-actions class="mb-3">
                <v-spacer />
                <router-link
                  to="/certificationsoverview"
                  tag="span"
                  class="pointerClass mx-3"
                >
                  <v-btn color="danger">Close</v-btn>
                </router-link>
                <v-btn
                  :disabled="!valid"
                  @click="sendUpdatedCertification"
                  color="success"
                  class="mr-3"
                  >Send</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import axios from "axios";

export default {
  name: "EditCertification",
  data() {
    return {
      benched: 1,
      valid: true,
      currentCertification: {},
      certificationName: "",
      certificationPrice: 0,
      certificationCurrency: "",
      certificationUrl: "",
      certificationSkillList: [],
      nameRules: [v => !!v || "Name is required!"],
      currencyRules: [v => !!v || "Currency is required!"],
      urlRules: [v => !!v || "Url is required!"]
    };
  },
  created() {
    this.currentCertification = this.certifications.find(
      ({ id }) => id == this.$route.params.id
    );
    this.certificationName = this.currentCertification.name;
    this.certificationPrice = this.currentCertification.price;
    this.certificationCurrency = this.currentCertification.currency;
    this.certificationUrl = this.currentCertification.url;
  },
  async mounted() {
    const token = JSON.parse(localStorage.getItem("token"));
    const { data } = await axios.get("http://localhost:8080/skills", {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token
      }
    });
    let temp = [];
    temp = await axios.get(
      "http://localhost:8080/certifications/" +
        this.currentCertification.id +
        "/skills",
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token
        }
      }
    );
    temp.data._embedded.skills.forEach(skill => {
      console.log(temp);
      this.certificationSkillList.push(skill.id);
    });
    this.skillsMutation(data._embedded.skills);
  },
  methods: {
    sendUpdatedCertification() {
      this.$store.dispatch("updateCertificationRequest", {
        certification: {
          id: this.currentCertification.id,
          name: this.certificationName,
          currency: this.certificationCurrency,
          price: this.certificationPrice,
          url: this.certificationUrl
        },
        skills: this.certificationSkillList
      });
      this.$router.push({ name: "Certificationsoverview" });
    },
    ...mapMutations(["skillsMutation"])
  },
  computed: {
    ...mapGetters(["certifications"]),
    ...mapGetters(["skills"])
  }
};
</script>
