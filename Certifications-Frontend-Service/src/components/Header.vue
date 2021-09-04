<template>
  <v-app-bar app dark>
    <router-link to="/" tag="span" class="pointerClass">
      <h2 class="font-bold">HOME</h2>
    </router-link>
    <v-spacer />
    <router-link to="/certificationsoverview" tag="span" class="pointerClass">
      <h2 class="navitem">Certifications</h2>
    </router-link>
    <v-spacer />

    <div v-if="admin" class="adminHeader">
      <router-link to="/all-users" tag="span" class="pointerClass">
        <h2 class="navitem">Users</h2>
      </router-link>
    </div>

    <router-link to="/voucher" tag="span" class="pointerClass">
      <h2 class="navitem">Vouchers</h2>
    </router-link>
    <v-spacer />

    <router-link to="/profile" tag="span" class="pointerClass">
      <span class="navitem">Profile</span>
      <v-icon>mdi-account</v-icon>
    </router-link>
    <v-spacer />

    <span class="pointerClass navitem" @click="logout">
      <span>Logout</span>
      <v-icon>mdi-logout</v-icon>
    </span>
  </v-app-bar>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "Header",
  methods: {
    logout() {
      this.$store.commit("loggedInMutation", false);
      localStorage.removeItem("loggedIn");
      localStorage.removeItem("user");
      localStorage.removeItem("token");
      localStorage.removeItem("admin");
      location.reload();
    }
  },
  computed: {
    ...mapGetters(["admin"])
  }
};
</script>

<style scoped>
.pointerClass {
  cursor: pointer;
}
.adminHeader {
  margin-right: 10%;
}

.navitem:hover{
  text-decoration: underline;
}
</style>
