<template>
  <BookForm v-if="showCreateFrom" :after-save="afterSave"/>
  <a v-on:click="showCreateFrom = true">Add</a>

  <ul class="">
    <li :key="book.id" v-for="(book, index) in books">
      <a v-if="editIndex!==index" v-on:click="view(book)">{{ book.name }}</a>
      <BookForm :book="book" v-if="editIndex===index" :after-save="afterSave"/>&nbsp;&nbsp;&nbsp;&nbsp;
      <a v-if="editIndex!==index" v-on:click="editIndex = index">Edit</a>
      <a v-if="editIndex!==index" v-on:click="remove(book)">Delete</a>
    </li>
  </ul>
</template>

<script>
import BookForm from "@/components/BookForm";

export default {
  name: "Books",
  components: {BookForm},
  data() {
    return {
      showCreateFrom: false,
      editIndex: null,
      books: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    view: function (book) {
      this.$parent.fetchBookmarks(book);
    },
    remove: function (book) {
      const requestOptions = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"},
      };
      fetch('/v1/book/' + book.id, requestOptions)
          .then(res => res.json())
          .then(() => {
            this.fetchData();
          });
    },
    fetchData: function () {
      fetch('/v1/book')
          .then(res => res.json())
          .then(res => {
            this.books = res.data.content;
          });
    },
    afterSave: function (book) {
      this.books[this.editIndex] = book;
      this.showCreateFrom = false;
      this.editIndex = null;
      this.fetchData();
    }
  }
}
</script>

<style scoped>

</style>