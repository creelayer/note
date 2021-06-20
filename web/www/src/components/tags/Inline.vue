<template>
  <ul class="tags">
    <li class="tag" style="color: #60717D">
      {{ bookName }}
    </li>
    <li :key="tag" v-for="tag in tags" :style="'background-color:#'+tag.color"
        class="tag">
      {{ tag.name }}
    </li>

    <li class="tag-select">
    <Multiselect v-if="addShow"
                 :options="allTags"
                 :mode="'single'"
                 :searchable="true"
                 :createTag="true"
                 :valueProp="'name'"
                 :label="'name'"
    />
    </li>


    <li class="tag add" v-on:click="add" v-if="!addShow">
      <i class="bi bi-plus-circle-dotted"></i>
    </li>
  </ul>
</template>

<script>

import Multiselect from '@vueform/multiselect'

export default {
  name: "Inline",
  components: {Multiselect},
  props: {
    bookName: null,
    tags: null
  },
  data() {
    return {
      addShow: false,
      allTags: null
    }
  },
  methods: {
    add: function () {
      this.fetchTags();
      this.addShow = true;
    },
    fetchTags: function () {
      fetch('/v1/tag')
          .then(res => res.json())
          .then(res => {
            this.allTags = res.data;
          });
    },
  }
}
</script>

<style scoped>

.tags .tag.add {
  background-color: transparent;
  margin-top: -4px;
}

.tags .tag.add i {
  color: black;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;

}

.tag-select{
  float: left;
}

.multiselect-input{
  border-bottom: 1px solid #60717D;
}

.multiselect-caret{
  display: none;
}

</style>