<template>
  <input type="email"
         class="form-control form-control-sm mb-2"
         placeholder="search tag..."
         v-model="model"
         v-on:keyup="search(model)"
         v-on:change="search(model)">
  <table class="table">
    <tbody>
    <tr :key="index" v-for="(tag, index) in tags">
      <td class="editable">

        <div class="float-start me-2 " v-if="editFromIndex!==tag.id">
          {{ tag.name }}
        </div>

        <input v-bind:id="'edit-'+tag.id" v-if="editFromIndex===tag.id" v-model="name"
               v-on:blur="eventTitleChanged($event, tag)" class="float-start me-2">

        <i class="edit bi bi-pencil float-start ms-2" v-on:click="actionEditTitle($event, tag)"
           v-if="editFromIndex!==tag.id"></i>

        <div class="color float-end mt-1 ">
          <Color :afterChoose="choose" :identity="tag.id" :current-color="tag.color"/>
        </div>

      </td>

    </tr>
    </tbody>
  </table>
</template>

<style scoped>
.editable i {
  cursor: pointer;
  font-size: 14px;
  opacity: 0;
}

.editable:hover i {
  color: #d0d0d0;
  opacity: 1;
}
</style>

<script>
import Color from '@/components/ColorPicker.vue';
import Rest from "@/api/Rest"

export default {
  name: "Tags",
  components: {Color},
  data() {
    return {
      tags: [],
      editFromIndex: false,
      name: null,
      model: '',
      tagsOriginal: []
    }
  },
  methods: {
    search: function (name) {
      if (name.length === 0) {
        this.tags = this.tagsOriginal;
        return;
      }
      this.tags = this.tagsOriginal.filter(t => t.name.includes(name));
    },
    fetchData: function () {
      Rest.get('/v1/tag')
          .then(res => {
            this.tags = this.tagsOriginal = res.data;
          });
    },
    choose: function (id, color) {
      let tag = this.tags.find(t => t.id === id);
      tag.color = color;
      Rest.post('/v1/tag/' + tag.id, tag).then(() => {
        this.fetchData();
      });
    },
    actionEditTitle: function (event, tag) {
      this.editFromIndex = tag.id;
      this.name = tag.name;
      this.$nextTick(() => {
        document.getElementById("edit-" + tag.id).focus();
      });
    },
    eventTitleChanged: function (event, tag) {
      this.editFromIndex = null;
      tag.name = this.name;
      Rest.post('/v1/tag/' + tag.id, tag).then(() => {
        this.fetchData();
      });
    },
  }
}
</script>

