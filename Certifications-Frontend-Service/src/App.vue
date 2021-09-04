<template>
  <v-app>
    <Header v-if="loggedIn" />
    <v-main class="bg-gradient-to-t from-blue-200 to-indigo-900">
      <router-view />
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import Footer from "./components/Footer.vue";
import Header from "./components/Header.vue";
import { mapGetters, mapMutations } from "vuex";

export default {
  components: {
    Footer,
    Header
  },
  computed: {
    ...mapGetters(["loggedIn"])
  },
  created() {
    this.loggedInMutation(localStorage.getItem("loggedIn"));
    if (this.loggedIn == false) {
      this.$router.push("/login");
    }
  },
  methods: {
    ...mapMutations(["loggedInMutation"])
  }
};
</script>

<style src="./assets/tailwind.css" lang="css"></style>
