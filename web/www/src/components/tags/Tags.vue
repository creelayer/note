<template>
  <table class="table">
    <tbody>
    <tr :key="tag.id" v-for="tag in tags">
      <td>



        <div class="float-start me-2">
          {{ tag.name }}
        </div>

        <div class="color float-start mt-1">
          <Color :afterChoose="choose" :identity="tag.id" :current-color="tag.color"/>
        </div>

      </td>

    </tr>
    </tbody>
  </table>
</template>

<style scoped>
.color {

}
</style>

<script>
import Color from '@/components/ColorPicker.vue';

export default {
  name: "Tags",
  components: {Color},
  data() {
    return {
      tags: []
    }
  },
  methods: {
    fetchData: function () {
      fetch('/v1/tag')
          .then(res => res.json())
          .then(res => {
            this.tags = res.data;
          });
    },
    choose: function (id, color) {

      let tag = this.tags.find(t => t.id === id);
      tag.color = color;
      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(tag)
      };
      fetch('/v1/tag/' + tag.id, requestOptions)
          .then(res => res.json())
          .then(() => {

          });
    }
  }
}
</script>

