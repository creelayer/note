<template>
  <div class="d-grid gap-2">
    <button v-on:click="showCreateFrom = true" class="btn btn-light mb-2" type="button">+ Add</button>
  </div>
  <BookForm v-if="showCreateFrom"/>

  <ul class="books">
    <li v-on:click="view(null)" v-bind:class="(!book ? ' border rounded-2 current':'')">
      <a >All books</a>
    </li>
    <li :key="book1.id" v-for="(book1, index) in books" v-on:click="view(book1)" v-bind:class="'context-menu '+(book && book1.id === book.id ? ' border rounded-2 current':'')">
      <a v-if="editFromIndex!==index">{{ book1.name }}</a>

      <div v-if="editFromIndex!==index" class="dropdown without-caret float-end">
        <button class="btn btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
          <i class="bi bi-three-dots-vertical"></i>
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
          <li><a class="dropdown-item" href="#" v-on:click="editFromIndex = index">Edit</a></li>
          <li><a class="dropdown-item" href="#" v-on:click="remove(book1)">Delete</a></li>
        </ul>
      </div>
      <BookForm :book="book1" v-if="editFromIndex===index"/>
    </li>
  </ul>

  <hr/>

  <ul class="books" v-on:click="showModal">
    <li>
      <a>Tags</a>
    </li>
  </ul>

  <Modal
      v-show="isModalVisible"
      @close="closeModal">
    <template v-slot:header>
      Tag editor
    </template>
    <template v-slot:body>
      <Tags ref="tags"/>
    </template>
  </Modal>

</template>


<style scoped>
.books {
  list-style: none;
  padding-left: 0;
}

.books > li {
  padding: 5px 10px;
  cursor: pointer;
  color: #60717D;
}

.books li:hover {
  color: #000;
}

</style>


<script>
import BookForm from "@/components/workTabke/BookForm";
import Tags from '@/components/tags/Tags.vue';
import Modal from '@/components/Modal.vue';
import Rest from "@/api/Rest"

export default {
  name: "Books",
  components: {BookForm, Modal, Tags},
  data() {
    return {
      book: null,
      showCreateFrom: false,
      editFromIndex: null,
      books: [],
      isModalVisible: false,
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    view: function (book) {
      this.book = book;
      this.$parent.fetchBookmarks(book);
    },
    remove: function (book) {
      Rest.del('/v1/book/' + book.id).then(() => {
        this.fetchData();
      })
    },
    fetchData: function () {
      this.showCreateFrom = false;
      this.editFromIndex = null;
      Rest.get('/v1/book').then(res => {
        this.books = res.data.content;
      });
    },
    showModal() {
      this.$refs.tags.fetchData();
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    }
  }
}
</script>

<style scoped>

</style>