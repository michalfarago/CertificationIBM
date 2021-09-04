<template>
  <div>
    <!-- component -->
    <main
      class="grid place-items-center min-h-screen p-5"
    >
      <div>
        <h1
          class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200 mb-5"
        >
          Voucher for you
        </h1>
        <div
          class="relative bg-white py-6 px-6 rounded-3xl w-86 my-4 shadow-xl"
        >
          <div
            class=" text-white flex items-center absolute rounded-full py-4 px-4 shadow-xl bg-green-500 left-4 -top-6"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24px"
              viewBox="0 0 24 24"
              width="24px"
              fill="#FFFFFF"
            >
              <path d="M0 0h24v24H0V0z" fill="none" />
              <path
                d="M8 16h8v2H8zm0-4h8v2H8zm6-10H6c-1.1 0-2 .9-2 2v16c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm4 18H6V4h7v5h5v11z"
              />
            </svg>
          </div>
          <div class="mt-8">
            <div class="flex space-x-2">
              <p>
                Voucher is <strong>{{ currentVoucher.state }}!</strong>
              </p>
            </div>
            <div class="flex space-x-2 text-md my-3">
              <!-- svg  -->
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                height="24px"
                viewBox="0 0 24 24"
                width="24px"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"
                />
              </svg>

              <p>
                Valid until
                <strong>{{ currentVoucher.validUntil | formatDate }}</strong>
              </p>
            </div>

            <div class="border-t-2"></div>
            <p class="text-xl font-semibold my-2">Certification for</p>
            <div class="flex space-x-2 text-gray-400 text-md my-2">
              <!-- svg  -->
              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="24px"
                viewBox="0 0 24 24"
                width="24px"
                fill="#000000"
              >
                <path d="M0 0h24v24H0V0z" fill="none" />
                <path
                  d="M9.4 16.6L4.8 12l4.6-4.6L8 6l-6 6 6 6 1.4-1.4zm5.2 0l4.6-4.6-4.6-4.6L16 6l6 6-6 6-1.4-1.4z"
                />
              </svg>
              <p>{{ voucherDetailData.name }}</p>
            </div>
            <div class="flex space-x-2 text-gray-400 text-md my-2">
              <!-- svg  -->

              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="24px"
                viewBox="0 0 24 24"
                width="24px"
                fill="#000000"
              >
                <path d="M0 0h24v24H0V0z" fill="none" />
                <path
                  d="M17 7h-4v2h4c1.65 0 3 1.35 3 3s-1.35 3-3 3h-4v2h4c2.76 0 5-2.24 5-5s-2.24-5-5-5zm-6 8H7c-1.65 0-3-1.35-3-3s1.35-3 3-3h4V7H7c-2.76 0-5 2.24-5 5s2.24 5 5 5h4v-2zm-3-4h8v2H8z"
                />
              </svg>

              <p>{{ voucherDetailData.url }}</p>
            </div>
            <div class="flex space-x-2 text-gray-400 text-md my-2">
              <!-- svg  -->

              <svg
                xmlns="http://www.w3.org/2000/svg"
                enable-background="new 0 0 24 24"
                height="24px"
                viewBox="0 0 24 24"
                width="24px"
                fill="#000000"
              >
                <g><rect fill="none" height="24" width="24" /></g>
                <g>
                  <g />
                  <path
                    d="M15,18.5c-2.51,0-4.68-1.42-5.76-3.5H15l1-2H8.58c-0.05-0.33-0.08-0.66-0.08-1s0.03-0.67,0.08-1H15l1-2H9.24 C10.32,6.92,12.5,5.5,15,5.5c1.61,0,3.09,0.59,4.23,1.57L21,5.3C19.41,3.87,17.3,3,15,3c-3.92,0-7.24,2.51-8.48,6H3l-1,2h4.06 C6.02,11.33,6,11.66,6,12s0.02,0.67,0.06,1H3l-1,2h4.52c1.24,3.49,4.56,6,8.48,6c2.31,0,4.41-0.87,6-2.3l-1.78-1.77 C18.09,17.91,16.62,18.5,15,18.5z"
                  />
                </g>
              </svg>
              <p>{{ voucherDetailData.price }}</p>
            </div>
            <div class="border-t-2"></div>

            <div class="flex justify-between">
              <div class="my-2">
                <p class="font-semibold text-base mb-2">Assigned Users</p>
                <div
                  class="flex space-x-2"
                  v-if="
                    voucherDetailUserData !== undefined &&
                      voucherDetailUserData != ''
                  "
                >
                  <v-icon>mdi-account</v-icon>
                  {{
                    voucherDetailUserData.name +
                      " " +
                      voucherDetailUserData.surname
                  }}
                </div>
                <div v-else>
                  <h3>No User Assigned!</h3>
                </div>
              </div>
            </div>
            <v-btn
              @click.stop="assignVoucherToUserDialog = true"
              color="success"
            >
              Choose User to assign</v-btn
            >
            <v-dialog
              v-model="assignVoucherToUserDialog"
              persistent
              max-width="600px"
            >
              <v-card>
                <v-card-title>
                  Assign Voucher To User
                </v-card-title>
                <v-card-text>
                  <v-form ref="assignUserForm">
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          v-model="selectedUser"
                          :items="listOfUsers.users"
                          item-text="surname"
                          item-value="id"
                          label="User"
                        >
                        </v-select>
                      </v-col>
                    </v-row>
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer />
                  <v-btn @click="closeAssignVoucherToUserDialog" color="danger"
                    >Close</v-btn
                  >
                  <v-btn @click="sendAssignUserDialog" color="success"
                    >Assign</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import Vue from "vue";
import moment from "moment";
import axios from "axios";

Vue.filter("formatDate", function(value) {
  if (value) {
    return moment(String(value)).format("DD/MM/YYYY hh:mm");
  }
});

export default {
  name: "VoucherDetail",
  data() {
    return {
      currentVoucher: {},
      assignVoucherToUserDialog: false,
      selectedUser: 0,
      users: {},
      fullNames: []
    };
  },
  created() {
    this.currentVoucher = this.vouchers.find(
      ({ id }) => id == this.$route.params.id
    );
  },
  computed: {
    ...mapGetters([
      "vouchers",
      "voucherDetailData",
      "voucherDetailUserData",
      "listOfUsers",
    ]),
  },
  async mounted() {
    this.mountedMock();
  },
  methods: {
    ...mapMutations([
      "voucherDetailMutation",
      "voucherDetailUserMutation",
      "listOfUsersMutation",
    ]),
    async mountedMock() {
      const token = JSON.parse(localStorage.getItem("token"));
      const voucherData = await axios.get(
        "http://localhost:8080/vouchers/" +
          this.currentVoucher.id +
          "/certification",
        {
          headers: {
            Authorization: "Bearer " + token,
          },
        }
      );

      const voucherUserData = await axios.get(
        "http://localhost:8080/vouchers/" + this.currentVoucher.id + "/user",
        {
          headers: {
            Authorization: "Bearer " + token,
          },
        }
      );

      const users = await axios.get("http://localhost:8080/users/", {
        headers: {
          Authorization: "Bearer " + token,
        },
      });

      this.voucherDetailMutation(voucherData.data);
      this.voucherDetailUserMutation(voucherUserData.data);
      this.listOfUsersMutation(users.data._embedded);
    },

    closeAssignVoucherToUserDialog() {
      this.assignVoucherToUserDialog = false;
    },
    sendAssignUserDialog() {
      this.$store.dispatch("assignVoucherToUserRequest", {
        userId: this.selectedUser,
        voucherId: this.currentVoucher.id,
      });
      this.mountedMock();
      this.closeAssignVoucherToUserDialog();
    },
  },
};
</script>

<style></style>
