<template>
  <!--  <div class="d-grid gap-2">-->
  <!--    <button v-on:click="showCreateFrom = true" class="btn btn-light mb-2" type="button">+ Add</button>-->
  <!--  </div>-->


  <div class="d-flex">
    <div class="w-100 border"> <a>Books list</a></div>
    <div class="flex-shrink-1">

      <div class="add-book" v-on:click="showCreateFrom = true">
        <div class="add-book-box">
          <svg class="Icon" focusable="false" viewBox="0 0 32 32">
            <path
                d="M26,14h-8V6c0-1.1-0.9-2-2-2l0,0c-1.1,0-2,0.9-2,2v8H6c-1.1,0-2,0.9-2,2l0,0c0,1.1,0.9,2,2,2h8v8c0,1.1,0.9,2,2,2l0,0c1.1,0,2-0.9,2-2v-8h8c1.1,0,2-0.9,2-2l0,0C28,14.9,27.1,14,26,14z"></path>
          </svg>
        </div>
      </div>


    </div>
  </div>

  <BookForm v-if="showCreateFrom"/>

  <ul class="books">
    <li v-on:click="view(null)" v-bind:class="'context-menu border '+(!book ? ' rounded-2 current':'')">
      <a>Uncategorized</a>
    </li>
    <li :key="book1.id" v-for="(book1, index) in books" v-on:click="view(book1)"
        v-bind:class="'context-menu border '+(book && book1.id === book.id ? ' rounded-2 current':'')">
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
      <div class="float-start">Tag editor</div>
    </template>
    <template v-slot:body>
      <Tags ref="tags"/>
    </template>
  </Modal>

</template>

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

.w-100{
  margin-right: 5px;
  padding: 4px 8px;
  margin-bottom: 10px;
  cursor: pointer;
}

.add-book {
  width: 100%;
  align-items: center;
  box-sizing: border-box;
  color: #6f7782;
  display: inline-flex;
  text-align: center;
  cursor: pointer;
}

.add-book-box {
  border-radius: 6px;
  height: 31px;
  width: 30px;
  margin: 0;
  align-items: center;
  border: 0 dashed #9ca6af;
  box-sizing: border-box;
  color: #6f7782;
  display: flex;
  fill: #9ca6af;
  justify-content: center;
  position: relative;
}

.Icon {
  flex: 0 0 auto;
  height: 16px;
  width: 16px;
}


.books {
  list-style: none;
  padding-left: 0;
}

.border{
  border-color: #fff !important;
}

.border.current{
  border-color: #14aaf5 !important;
  background-color: #edf8ff;
}

.books > li {
  padding: 5px 10px;
  cursor: pointer;
  color: #60717D;
}

.books li:hover {
  color: #000;
}

.books a span {
  font-weight: bold;
  font-size: 16px;
}

li.book-form {
  padding: 0;
}

.modal-body table {
  overflow-y: scroll;
}
</style>