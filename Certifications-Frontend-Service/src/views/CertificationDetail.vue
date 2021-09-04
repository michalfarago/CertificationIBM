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
                Certification detail
              </h1>
            </v-layout>
            <v-card style="border-radius:25px; padding:10px;">
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
              <div class="mt-7">
                <v-row>
                  <v-col cols="12" class="flex px-5">
                    <v-icon>mdi-certificate-outline</v-icon>
                    <v-card-text style="font-size:1.2rem" label="Name">{{
                      currentCertification.name
                    }}</v-card-text>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12" class="py-0 flex px-5">
                    <v-icon>mdi-cash-multiple</v-icon>
                    <v-card-text style="font-size:1.2rem" label="Price"
                      >{{ currentCertification.price }}
                      {{ currentCertification.currency }}</v-card-text
                    >
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12" class="flex px-5">
                    <v-icon>mdi-web</v-icon>
                    <v-card-text style="font-size:1.2rem" label="Url"
                      >{{ currentCertification.url }}
                    </v-card-text>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12" style="px-5">
                    <p class="text-center">
                      Skills obtained with this certification
                    </p>
                  </v-col>
                </v-row>
                <v-row class="my-0">
                  <v-list-item
                    v-for="skill in certificationSkills"
                    :key="skill.name"
                    two-line
                    style="border: solid black 1px; margin:10px;"
                  >
                    <v-list-item-title>{{ skill.name }}</v-list-item-title>
                    <v-list-item-subtitle>{{
                      skill.description
                    }}</v-list-item-subtitle>
                  </v-list-item>
                </v-row>
              </div>
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
                  color="success"
                  class="mr-3"
                  @click="routeToEditCertification"
                  >Edit</v-btn
                >
                <v-btn @click="deleteCertificationDialog = true" color="danger"
                  >Delete</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-flex>
    </v-layout>
    <v-row justify="center">
      <v-dialog persistent v-model="deleteCertificationDialog" max-width="290">
        <v-card>
          <v-card-title class="text-h5">
            Delete certification?
          </v-card-title>

          <div v-if="hasAffectedVouchers">
            <v-card-subtitle v-if="hasAffectedVouchers">
              Vouchers affected:
            </v-card-subtitle>

            <v-list-item
              v-for="voucher in certificationVouchers"
              :key="voucher.voucherCode"
            >
              <v-list-item-title>{{ voucher.voucherCode }}</v-list-item-title>
            </v-list-item>
          </div>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn
              color="black darken-1"
              text
              @click="deleteCertificationDialog = false"
            >
              Close
            </v-btn>

            <v-btn color="red darken-1" text @click="deleteCertification">
              Delete
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "CertificationDetail",
  data() {
    return {
      currentCertification: {},
      certificationSkills: [],
      certificationVouchers: [],
      deleteCertificationDialog: false,
      hasAffectedVouchers: false
    };
  },
  async created() {
    this.currentCertification = this.certifications.find(
      ({ id }) => id == this.$route.params.id
    );
  },
  async mounted() {
    await this.fetchSkills();
    await this.fetchVouchers();
  },
  computed: {
    ...mapGetters(["certifications"]),
    certificationHasState() {
      return this.currentCertification.state != null;
    }
  },
  methods: {
    async deleteCertification() {
      if (Object.keys(this.currentCertification) === 0) {
        return;
      }
      const token = JSON.parse(localStorage.getItem("token"));
      const url =
        "http://localhost:8080/delete-certification/" +
        this.currentCertification.id;
      try {
        const { data } = await axios.delete(url, {
          headers: {
            Authorization: "Bearer " + token
          }
        });
        this.currentCertification = {};
        this.$router.push("/certificationsoverview");
      } catch (error) {
        console.log(error);
      }
    },
    async fetchSkills() {
      const token = JSON.parse(localStorage.getItem("token"));
      const url =
        "http://localhost:8080/certification-skills/" +
        this.currentCertification.id;
      try {
        const { data } = await axios.get(url, {
          headers: {
            Authorization: "Bearer " + token
          }
        });
        this.certificationSkills = data;
      } catch (error) {
        console.log(error);
      }
    },

    async fetchVouchers() {
      const token = JSON.parse(localStorage.getItem("token"));
      const url =
        "http://localhost:8080/certification-vouchers/" +
        this.currentCertification.id;
      try {
        const { data } = await axios.get(url, {
          headers: {
            Authorization: "Bearer " + token
          }
        });
        this.certificationVouchers = data;
        this.hasAffectedVouchers =
          this.certificationVouchers != null &&
          this.certificationVouchers.length > 0;
      } catch (error) {
        console.log(error);
      }
    },
    routeToEditCertification() {
      this.$router.push(
        "/edit-certificate-form/" + this.currentCertification.id
      );
    },
    routeToVoucherDetail() {
      this.$router.push("/voucherDetail/" + this.currentCertification.id);
    }
  }
};
</script>

<style></style>
