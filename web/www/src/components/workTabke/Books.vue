<template>
  <div class="d-grid gap-2">
    <button v-on:click="showCreateFrom = true" class="btn btn-light mb-2" type="button">+ Add</button>
  </div>
  <BookForm v-if="showCreateFrom" :after-save="afterSave"/>

  <ul class="books">
    <li>
      <a v-on:click="view(null)">All books</a>
    </li>
    <li :key="book.id" v-for="(book, index) in books" v-on:click="view(book)" class="context-menu">
      <a v-if="editIndex!==index">{{ book.name }}</a>

      <div v-if="editIndex!==index" class="dropdown without-caret float-end">
        <button class="btn btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
          <i class="bi bi-three-dots-vertical"></i>
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
          <li><a class="dropdown-item" href="#" v-on:click="editIndex = index">Edit</a></li>
          <li><a class="dropdown-item" href="#" v-on:click="remove(book)">Delete</a></li>
        </ul>
      </div>
      <BookForm :book="book" v-if="editIndex===index" :after-save="afterSave"/>
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
  font-size: 1em;
  padding: 8px 10px;
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

export default {
  name: "Books",
  components: {BookForm, Modal, Tags},
  data() {
    return {
      book: null,
      showCreateFrom: false,
      editIndex: null,
      books: [],
      isModalVisible: false,
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    view: function (book) {
      this.$parent.book = book;
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