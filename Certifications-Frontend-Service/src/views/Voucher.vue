<template>
  <div class="home">
    <v-row>
      <v-col cols="12">
        <v-row>
          <v-col cols="12" style="padding-top: 1em; padding-bottom: 1em">
            <v-dialog v-model="newVoucherDialog" persistent max-width="600px">
              <v-card>
                <v-card-title>
                  New Voucher Request
                </v-card-title>
                <v-card-text>
                  <v-form ref="newVoucherForm" v-model="valid">
                    <v-row>
                      <v-col class="d-flex" cols="12" sm="6">
                        <v-select
                          v-model="selectedState"
                          :items="voucherState"
                          label="State"
                          required
                          :rules="stateRules"
                        ></v-select>
                      </v-col>
                      <v-col cols="6">
                        <v-text-field
                          label="Voucher Code"
                          type="text"
                          :rules="voucherRules"
                          v-model="voucherCode"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="selectedCertification"
                          :items="this.$store.getters.certifications"
                          item-text="name"
                          item-value="id"
                          label="Certification"
                          required
                          :rules="certificationRules"
                        >
                        </v-select>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="selectedUser"
                          :items="this.$store.getters.listOfUsers.users"
                          item-text="surname"
                          item-value="id"
                          label="User"
                        >
                        </v-select>
                      </v-col>
                    </v-row>
                    <v-row justify="center">
                      <v-date-picker v-model="validUntil"></v-date-picker>
                    </v-row>
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer />
                  <v-btn @click="closeNewVoucherDialog" color="danger"
                    >Close</v-btn
                  >
                  <v-btn
                    :disabled="!valid"
                    @click="sendNewVoucherDialog"
                    color="success"
                    >Send</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12">
            <v-layout align-center justify-center>
              <h1
                class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200 mt-3"
              >
                <h1 class="text-center">Available Vouchers</h1>
              </h1>
            </v-layout>
          </v-col>
        </v-row>
        <v-flex justify-center>
          <v-btn
            @click.stop="newVoucherDialog = true"
            color="primary"
            class="my-3"
            >New Voucher Request
          </v-btn>
        </v-flex>
        <div v-if="vouchers !== undefined && vouchers.length > 0">
          <v-row>
            <v-col>
              <v-layout d-flex flex-wrap justify-space-around>
                <router-link
                  v-for="voucher in vouchers"
                  :key="voucher.id"
                  :to="'/voucherDetail/' + voucher.id"
                  tag="div"
                >
                  <VoucherItem style="cursor: pointer" :voucher="voucher" />
                </router-link>
              </v-layout>
            </v-col>
          </v-row>
        </div>
        <div v-else>
          <h3>No Vouchers found</h3>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Vue from "vue";
import VoucherItem from "@/components/VoucherItem.vue";
import { mapGetters, mapMutations } from "vuex";
import axios from "axios";

export default {
  name: "Voucher",
  components: {
    VoucherItem
  },
  data() {
    return {
      valid: true,
      newVoucherDialog: false,
      voucherState: ["NEW", "ACTIVE", "PROPOSED"],
      voucherCode: "",
      validUntil: new Date().toISOString().substr(0, 10),
      voucherRules: [
        value => value.length >= 5 || "Minimum 5 characters required"
      ],
      certificationRules: [value => value != null || "Item is not selected!"],
      stateRules: [value => value != "" || "Item is not selected"],
      selectedUser: null,
      selectedCertification: null,
      selectedState: ""
    };
  },
  computed: {
    ...mapGetters(["vouchers", "listOfUsers", "certifications"])
  },
  created() {
    this.$store.dispatch("loadDatasForVouchers");
  },
  methods: {
    ...mapMutations([
      "vouchersMutation",
      "listOfUsersMutation",
      "certificationsMutation"
    ]),
    closeNewVoucherDialog() {
      this.newVoucherDialog = false;
      this.voucherCode = "";
      this.validUntil = "";
      this.voucherState = ["NEW", "ACTIVE", "PROPOSED"];
      this.selectedUser = null;
      this.selectedCertification = null;
    },
    sendNewVoucherDialog() {
      this.$store.dispatch("createVoucherRequest", {
        state: this.selectedState,
        validUntil: this.validUntil,
        voucherCode: this.voucherCode,
        certificationId: this.selectedCertification,
        userId: this.selectedUser
      });
      this.closeNewVoucherDialog();
      this.$store.dispatch("loadDatasForVouchers");
    }
  }
};
</script>

<style></style>
