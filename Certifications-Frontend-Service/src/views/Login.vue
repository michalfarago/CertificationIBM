<template>
  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <v-flex md4 sm8 xs12>
        <div class="relative sm:max-w-sm w-full">
          <div
            class="card shadow-lg  w-full h-full rounded-3xl absolute  transform -rotate-6" style="background-color: #679b7c;"
          ></div>
          <div
            class="card shadow-lg  w-full h-full rounded-3xl absolute  transform rotate-6" style="background-color: #c1fff4;"
          ></div>
          <v-card class="elevation-12" style="background-color:#f9fafb; border:0;">
            <v-card-text>
              <h2 class="text-center text-3xl font-bold my-4">
                Welcome to CertificationApp
              </h2>
              <p class="text-center">Please Enter your credentials</p>
              <v-form ref="loginForm" v-model="validForm">
                <v-text-field
                  prepend-icon="mdi-account"
                  label="Enter your email"
                  type="text"
                  :counter="50"
                  :rules="emailRules"
                  v-model="email"
                ></v-text-field>
                <v-text-field
                  prepend-icon="mdi-fingerprint"
                  label="Enter your password"
                  type="password"
                  :rules="passwordRules"
                  v-model="password"
                ></v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-btn :disabled="!validForm" @click="loginToApp" style="background-color: #c1fff4; width:100%">
                Login</v-btn
              >
            </v-card-actions>
            <div>
              <v-row class="mt-2">
                <v-col cols="12" style="padding-top: 1em">
                  <h3 class="text-center">Don't have an account?</h3>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" style="padding-top: 0.5em" class="text-center">
                  <v-btn @click="registerToApp" style="background-color: #679b7c; width:50%;color:white;">
                    Sign up
                  </v-btn>
                </v-col>
              </v-row>
            </div>
          </v-card>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "Login",
  data() {
    return {
      validForm: true,
      emailRules: [
        value => !!value || "Email is required!",
        value => /.+@.+/.test(value) || "Email must be valid"
      ],
      passwordRules: [value => value.length >= 8 || "Minimum 8 characters"]
    };
  },
  computed: {
    email: {
      get() {
        return this.$store.getters.email;
      },
      set(value) {
        this.$store.commit("emailMutation", value);
      }
    },
    password: {
      get() {
        return this.$store.getters.password;
      },
      set(value) {
        this.$store.commit("passwordMutation", value);
      }
    }
  },
  methods: {
    ...mapActions(["loginToApp"]),
    registerToApp() {
      this.$router.push("/register");
    }
  }
};
</script>
