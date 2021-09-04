<template>
  <div>
    <v-row>
      <v-col cols="12">
        <v-layout align-center justify-center>
          <h1
            class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200 mt-3"
          >
            <h1 class="text-center">Users</h1>
          </h1>
        </v-layout>
      </v-col>
    </v-row>
    <v-simple-table class="m-5">
      <flash-message class="myCustomClass"></flash-message>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-center" style="color:black;">
              Name
            </th>
            <th class="text-center" style="color:black;">
              Surname
            </th>
            <th class="text-center" style="color:black;">
              Email
            </th>
            <th class="text-center" style="color:black;">ACTIONS</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.id">
            <td class="text-center">{{ user.name }}</td>
            <td class="text-center">{{ user.surname }}</td>
            <td class="text-center">{{ user.email }}</td>
            <td class="text-center">
              <v-btn color="danger" @click="removeUser(user.id)"
                >Remove User</v-btn
              >
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import VueFlashMessage from "vue-flash-message";

Vue.use(VueFlashMessage);

export default {
  name: "All-users",
  created() {
    this.$store.dispatch("loadUsers");
  },
  methods: {
    async removeUser(id) {
      const token = JSON.parse(localStorage.getItem("token"));
      try {
        await axios.delete("http://localhost:8080/api/test/delete-user/" + id, {
          headers: {
            Authorization: "Bearer " + token
          }
        });
      } catch (err) {
        if (err.response) {
          alert("RESPONSE ERROR - client received an error response");
        } else if (err.request) {
          alert("REQUEST ERROR - client never received a response");
        } else {
          alert("something wrong");
        }
      }
      this.$store.dispatch("loadUsers");
    }
  },
  computed: {
    users: {
      get() {
        return this.$store.getters.users;
      },
      set(value) {
        this.$store.commit("usersMutation", value);
      }
    }
  }
};
</script>
