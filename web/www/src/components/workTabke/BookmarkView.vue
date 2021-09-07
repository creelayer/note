<template>

  <div class="view">


    <div v-if="bookmark">

      <h3 v-if="!editTile">{{ name }} <i class="bi bi-pencil" v-on:click="actionEditTitle"></i></h3>

      <TextareaAutoSize v-if="editTile" v-model="name" v-on:blur="eventTitleChanged" ref="titleEditAutoSize"/>

      <InlineTags :bookmark="bookmark" :on-change="eventTagsChanged" :editable="true"/>

      <div class="form-check form-switch float-end" v-on:click="actionEditBody" v-if="body !==null">
        <label class="form-check-label" for="flexSwitchCheckDefault">Edit mode</label>
        <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" v-model="editBody">
      </div>

      <div class="body" v-if="!editBody" v-html="body"></div>
      <editor v-if="editBody" class="body"
              api-key="p2a5uegtxuz8zy6ti3jealzzijtkrtjoka48jqkzk9r26gw9"
              v-model="body"
              v-on:blur="eventBodyChanged"
              ref="bodyEditor"
              :init="{
                 menubar: false,
                 inline: true,
                 placeholder:'Click here to add some notates...',
                 plugins: [],
                 toolbar: 'formatselect | ' +
                  'bold italic backcolor | bullist numlist outdent indent | ' +
                  'removeformat',
               }"
      />
    </div>

  </div>
</template>

<style scoped>

.view {
  padding: 24px 20px;
  box-shadow: -0.5rem 0.5rem 1rem rgba(0, 0, 0, 0.02), inset 1px 0 0 rgba(0, 0, 0, 0.15);
  height: 100%;
  width: 100%;
  margin-bottom: -24px;
}

.body {
  margin-top: 12px;
}

.mce-content-body {
  min-height: 100px;
  padding: 4px;
}

h3 i {
  cursor: pointer;
  font-size: 16px;
  margin-top: -10px;
  color: #d0d0d0;
}

h1 i:hover {
  color: #000000;
}

.tags {
  position: relative;
  z-index: -2;
}

.edit-title {
  width: 100%;
}

.form-switch {
  position: relative;
  z-index: 0;
  margin-top: -26px;
  width: 136px;
}

.form-switch input {
  cursor: pointer;
  float: right;
}

.form-switch label {
  float: left;
  color: #d0d0d0;
  font-size: 12px;
  margin-top: 3px;
}

</style>

<script>
import InlineTags from "@/components/tags/Inline.vue";
import Editor from '@tinymce/tinymce-vue'
import TextareaAutoSize from "@/components/TextareaAutoSize.vue";
import Rest from "@/api/Rest"
import Debounce from "debounce";

export default {
  name: "BookmarkView",
  components: {InlineTags, Editor, TextareaAutoSize},
  data() {
    return {
      bookmark: null,
      editBody: false,
      editTile: false,

      name: '',
      body: '',
      tags: [],
    }
  },
  updated() {
  },
  methods: {
    fetchData: function (bookmark) {
      this.editTile = false;
      this.editBody = false;
      Rest.get('/v1/bookmark/' + bookmark.id)
          .then(res => {
            console.log(res);
            this.bookmark = res.data;
            this.name = this.bookmark.name;
            this.body = this.bookmark.body;
            this.tags = this.bookmark.tags;
            if (!this.body) {
              this.editBody = true;
            }
          });
    },
    saveData: Debounce(function () {
      let bookmark = {
        bookId: this.bookmark.bookId,
        name: this.name,
        body: this.body,
        tags: this.tags
      };
      Rest.post('/v1/bookmark/' + this.bookmark.id, bookmark)
          .then(res => {
            console.log(res);
          });

    }, 100),
    actionEditTitle: function () {
      this.editTile = true;
      this.editBody = false;
      this.$nextTick(() => {
        this.$refs.titleEditAutoSize.$el.focus();
      });
    },
    actionEditBody: function () {
      this.editTile = false;
      this.editBody = true;
    },
    eventTitleChanged: function () {
      this.editTile = false;
      this.saveData();
    },
    eventBodyChanged: function () {
      this.saveData();
    },
    eventTagsChanged: function (tags) {
      this.tags = tags;
      this.saveData();
    }
  }
}
</script>

