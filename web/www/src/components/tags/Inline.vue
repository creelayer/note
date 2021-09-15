<template>
  <ul class="tags">
    <li class="tag" style="color: #60717D">
      {{ bookmark.book.name }}
    </li>
    <li :key="tag" v-for="tag in currentTags" :style="'background-color:#'+tag.color"
        class="tag">
      {{ tag.name }}
      <i class="bi bi-x-lg" v-on:click="deleteTag(tag)" v-if="editable"></i>
    </li>

    <li class="tag-select">
      <form @submit="submit">
        <input name="name" autocomplete="off" v-on:keyup="filterTag" v-model="word" v-if="showAddForm" class="tag-input"
               placeholder="Input tag name">
      </form>
    </li>

    <li class="tag add" v-on:click="add" v-if="!showAddForm && editable">
      <i class="bi bi-plus-circle-dotted"></i>
    </li>
  </ul>

  <ul class="tags available-tags" v-if="showAddForm">
    <li class="tag" :key="tag.id" v-for="tag in filteredTags">
      <a href="#" v-on:click="selectTag(tag)">{{ tag.name }}</a>
    </li>
  </ul>

</template>

<script>
import Rest from "@/api/Rest"

export default {
  name: "Inline",
  props: {
    editable: null,
    bookmark: null,
    onChange: Function
  },
  watch: {
    bookmark: function (o) {
      this.currentTags = o.tags;
      this.showAddForm = false;
    }
  },
  data() {
    return {
      showAddForm: false,
      word: null,
      currentTags: this.bookmark.tags,
      availableTags: [],
      filteredTags: []
    }
  },
  methods: {
    add: function () {
      this.fetchTags();
      this.showAddForm = true;
    },
    filterTag: function () {
      this.filteredTags = this.availableTags.filter(tag => this._startWith(tag.name, this.word) && !this._includes(tag));
    },
    selectTag: function (tag) {
      this.currentTags.push(tag ? tag : {id: null, name: this.word})
      if (this.onChange != null) {
        this.onChange(this.currentTags);
      }
      this.showAddForm = false;
    },
    deleteTag: function (tag) {
      this.currentTags = this.currentTags.filter(e => e.name.toLowerCase() !== tag.name.toLowerCase());
      this.filteredTags = this.availableTags.filter(tag => !this._includes(tag)).slice(0, 10);
      if (this.onChange != null) {
        this.onChange(this.currentTags);
      }
    },
    submit: function (e) {
      e.preventDefault();
      let tag = this.availableTags.find(e => e.name.toLowerCase() === this.word.toLowerCase())
      this.selectTag(tag ? tag : {id: null, name: this.word});
    },
    fetchTags: function () {
      Rest.get('/v1/tag')
          .then(res => {
            this.availableTags = res.data;
            this.filteredTags = this.availableTags.filter(tag => !this._includes(tag)).slice(0, 10);
          });
    },
    _startWith: function (name, searchString) {
      if (!searchString) return true;
      return name.toLowerCase().substr(0, searchString.length) === searchString.toLowerCase();
    },
    _includes: function (check) {
      for (let tag of this.currentTags) {
        if (tag.name.toLowerCase() === check.name.toLowerCase()) {
          return true;
        }
      }
      return false;
    }
  }
}
</script>

<style scoped>

.tag-input {
  background-color: #fff;
  border: 0;
  border-bottom: 1px solid #d0d0d0;
  font-size: 12px;
  line-height: 1;
  margin-left: 8px;
  margin-top: -3px;
  float: left;
}

.tag-input:focus {
  outline: none;
}

.tags {
  margin: 10px 1px 0px;
  padding: 0;
  position: relative;
  display: block;
  overflow: hidden;
  list-style: none;
}

.tags .tag {
  margin-right: 6px;
  font-weight: normal;
  background-color: #e5e4e4;
  float: left;
  padding: 1px 6px;
  color: #FFFFFF;;
  font-size: 11px;
  border-radius: 8px;
}

.tags .tag.add {
  background-color: transparent;
  margin-top: -4px;
}

.tags .tag a {
  color: black;
}

.tags .tag i {
  margin-left: 4px;
  cursor: pointer;
}

.tags .tag.add i {
  color: black;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;

}

.tag-select {
  float: left;
}


</style>