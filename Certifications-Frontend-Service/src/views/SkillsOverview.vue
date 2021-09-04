<template>
  <div class="home">
    <v-row>
      <v-col cols="12">
        <div v-if="this.skills !== undefined && this.skills.length > 0">
          <v-row>
            <v-col cols="12">
              <v-layout align-center justify-center>
                <h1
                  class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200"
                >
                  <h1 class="text-center mt-3">Available Skills</h1>
                </h1>
              </v-layout>
            </v-col>
          </v-row>
          <v-flex justify-center>
            <v-btn
              @click.stop="newSkillDialog = true"
              color="primary"
              class="my-3"
              >Add New Skill
            </v-btn>
          </v-flex>
          <v-row>
            <v-col>
              <v-layout d-flex flex-wrap justify-space-around>
                <div v-for="skill in this.skills" tag="div" :key="skill.id">
                  <SkillItem :skill="skill" ref="skillsOver" />
                </div>
              </v-layout>
            </v-col>
          </v-row>
        </div>
        <div v-else>
          <v-container fluid fill-height>
            <v-row>
              <v-col cols="12">
                <v-row>
                  <v-col cols="12">
                    <v-layout align-center justify-center>
                      <h1
                        class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200"
                      >
                        <h1 class="text-center mt-3">No Skills Found</h1>
                      </h1>
                    </v-layout>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12">
                    <v-flex justify-center>
                      <v-btn
                        @click.stop="newSkillDialog = true"
                        color="primary"
                        class="my-3"
                        >Add New Skill
                      </v-btn>
                    </v-flex>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-container>
        </div>
        <v-dialog v-model="newSkillDialog" persistent max-width="600px">
          <v-card>
            <v-card-title>
              New Skill
            </v-card-title>
            <v-card-text>
              <v-form ref="newSkillForm" v-model="valid">
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="Name"
                      v-model="skillName"
                      prepend-icon="mdi-format-text-variant-outline"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="Description"
                      v-model="skillDescription"
                      prepend-icon="mdi-clipboard-text-outline"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-spacer />
              <v-btn @click="closeNewSkillDialog" color="danger">Close</v-btn>
              <v-btn
                :disabled="!valid"
                @click="sendNewSkillDialog"
                color="success"
                >Send</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Vue from "vue";
import SkillItem from "@/components/SkillItem.vue";
import axios from "axios";

export default Vue.extend({
  name: "Home",
  components: {
    SkillItem
  },
  created() {
    this.$root.$refs.skillsOver = this;
    console.log(this.$root.$refs.skillsOver);
  },
  data() {
    return {
      skills: [],
      valid: true,
      newSkillDialog: false,
      skillName: "",
      skillDescription: ""
    };
  },
  async mounted() {
    const token = JSON.parse(localStorage.getItem("token"));
    const { data } = await axios.get("http://localhost:8080/skills", {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token
      }
    });
    this.skills = data._embedded.skills;
  },
  methods: {
    closeNewSkillDialog() {
      this.newSkillDialog = false;
      this.skillDescription = "";
      this.skillName = "";
    },
    sendNewSkillDialog() {
      this.$store
        .dispatch("createSkillRequest", {
          name: this.skillName,
          description: this.skillDescription
        })
        .then(() => this.gettSkills());
      this.closeNewSkillDialog();
    },
    async gettSkills() {
      const token = JSON.parse(localStorage.getItem("token"));
      let temp = [];
      temp = await axios.get("http://localhost:8080/skills", {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token
        }
      });
      console.log(temp);
      this.skills = temp.data._embedded.skills;
    }
  }
});
</script>
