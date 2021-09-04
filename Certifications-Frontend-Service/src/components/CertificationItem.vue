<template>
  <div class="card-padding">
    <v-card width="300" elevation="12" style="border: 0;">
      <v-img
        class="white--text align-end"
        height="120px"
        src="https://picsum.photos/id/20/400/400"
      >
        <v-card-title
          style="color:#2d4a79; text-shadow: rgb(154 141 141) 1px 1px;"
          >{{ certification.name }}</v-card-title
        >
      </v-img>
      <div class="card-text-fix">
        <v-card-text class="align-end fill-height">
          <strong>
            {{ certification.price }} {{ certification.currency }}
          </strong>
          <router-link
            :to="'/edit-certificate-form/' + certification.id"
            tag="span"
            class="pointerClass"
            style="float:RIGHT;"
          >
            <v-btn color="danger">
              <span>EDIT</span>
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
          </router-link>
        </v-card-text>
      </div>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    certification: {
      type: Object,
      require: true
    }
  }
};
</script>

<style scoepd>
.card-padding {
  padding: 30px;
}
.card-text-fix {
  height: 60px;
}
</style>
