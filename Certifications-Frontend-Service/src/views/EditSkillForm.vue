<template>
  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <v-flex md6 sm8 xs12>
        <v-row>
          <v-col cols="12">
            <v-layout align-center justify-center>
              <h1
                class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200 mb-7"
              >
                Edit Skill
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
                <v-form ref="newSkillForm" v-model="valid">
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="Name"
                        v-model="skillName"
                        :rules="nameRules"
                        prepend-icon="mdi-format-text-variant-outline"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="Description"
                        :rules="descriptionRules"
                        v-model="skillDescription"
                        prepend-icon="mdi-clipboard-text-outline"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <router-link
                  to="/skillsoverview"
                  tag="span"
                  class="pointerClass mx-3"
                >
                  <v-btn color="danger">Close</v-btn>
                </router-link>
                <v-btn color="success" :disabled="!valid" @click="sendUpdatedSkill" class="mr-3"
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
import Vue from "vue";
import { mapGetters } from "vuex";
import axios from "axios";

export default Vue.extend({
  name: "EditSkill",
  data() {
    return {
      valid: true,
      currentSkillId: 0,
      skillName: "",
      skillDescription: "",
      nameRules: [v => !!v || "Name is required!"],
      descriptionRules: [v => !!v || "Description is required!"]
    };
  },
  async created() {
    const token = JSON.parse(localStorage.getItem("token"));
    const { data } = await axios.get(
      "http://localhost:8080/skills/" + this.$route.params.id,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token
        }
      }
    );

    this.skillName = data.name;
    this.skillDescription = data.description;
    this.currentSkillId = data.id;
  },
  computed: {
    ...mapGetters(["skills"])
  },
  methods: {
    sendUpdatedSkill() {
      this.$store.dispatch("updateSkillRequest", {
        id: this.currentSkillId,
        name: this.skillName,
        description: this.skillDescription
      });
      this.skillName = "";
      this.skillDescription = "";
      this.$router.push({ name: "SkillsOverview" });
    }
  }
});
</script>
