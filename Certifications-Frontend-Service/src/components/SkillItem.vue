<template>
  <div class="card-padding">
    <v-card width="300" elevation="12" style="border: 0;">
      <v-card-title
        ><span class="mx-auto">{{ skill.name }}</span></v-card-title
      >
      <div class="card-text-fix">
        <v-card-text class="align-end fill-height text-center">
          <strong>
            {{ skill.description }}
          </strong>
        </v-card-text>
      </div>
      <div class="text-center">
        <router-link
          :to="'/edit-skill-form/' + skill.id"
          tag="span"
          class="pointerClass"
        >
          <v-btn color="danger" class="mr-2 mb-2">
            <span>EDIT</span>
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
        </router-link>
        <v-btn color="danger" class="ml-2 mb-2" @click="sendDeleteSkill">
          <span>DELETE</span>
          <v-icon>mdi-trash-can-outline</v-icon>
        </v-btn>
      </div>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: {
    skill: {
      type: Object,
      require: true
    }
  },
  methods: {
    sendDeleteSkill() {
      const token = JSON.parse(localStorage.getItem("token"));
      axios
        .delete(
          "http://localhost:8080/delete-skill?id=" + this.$props.skill.id,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: "Bearer " + token
            }
          }
        )
        .then(() => this.$root.$refs.skillsOver.gettSkills());
    }
  }
};
</script>
