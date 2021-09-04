<template>
  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <v-flex md4 sm8 xs12>
        <v-card class="elevation-12">
          <v-toolbar dark color="dark">
            <v-toolbar-title>Register to Certification app</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <p>Please Enter your credentials</p>
            <v-form ref="registerForm" v-model="validForm">
              <v-text-field
                prepend-icon="mdi-account"
                label="Enter your first name"
                type="text"
                v-model="firstname"
                :rules="firstNameRules"
              ></v-text-field>
              <v-text-field
                prepend-icon="mdi-account"
                label="Enter your surname"
                type="text"
                v-model="surname"
                :rules="surnameRules"
              ></v-text-field>
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
                required
              ></v-text-field>
              <v-text-field
                prepend-icon="mdi-fingerprint"
                label="Please Re-enter your password"
                type="password"
                v-model="confirmPassword"
                :rules="confirmPasswordRules.concat(passwordConfirmationRule)"
                required
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-btn
              :disabled="!validForm"
              @click="sendNewRegisterRequest"
              class="success"
            >
              Register</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      validForm: true,
      firstname: "",
      firstNameRules: [value => !!value || "First name is required!"],
      surname: "",
      surnameRules: [value => !!value || "Surname is required!"],
      email: "",
      emailRules: [
        value => !!value || "Email is required!",
        value => /.+@.+/.test(value) || "Email must be valid"
      ],
      password: "",
      confirmPassword: "",
      passwordRules: [value => value.length >= 8 || "Minimum 8 characters"],
      confirmPasswordRules: [v => !!v || "Password is required"]
    };
  },
  computed: {
    passwordConfirmationRule() {
      return () =>
        this.password === this.confirmPassword || "Password must match";
    }
  },
  methods: {
    sendNewRegisterRequest() {
      this.$store.dispatch("sendNewRegisterRequest", {
        firstname: this.firstname,
        surname: this.surname,
        email: this.email,
        password: this.password
      });
    }
  }
};
</script>
