<template>
  <div class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header">
          This is the default title!
        </slot>
        <button
            type="button"
            class="btn-close"
            @click="close">
          <i class="bi bi-x-lg"></i>
        </button>
      </header>

      <section class="modal-body">
        <slot name="body">
          This is the default body!
        </slot>
      </section>

<!--      <footer class="modal-footer">-->
<!--        <slot name="footer">-->
<!--        </slot>-->
<!--        <button-->
<!--            type="button"-->
<!--            class="btn btn-secondary"-->
<!--            @click="close">Close</button>-->
<!--      </footer>-->
    </div>
  </div>
</template>

<script>
export default {
  name: 'Modal',
  methods: {
    close() {
      this.$emit('close');
    },
  },
};
</script>

<style scoped>
.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  position: relative;
  background: #FFFFFF;
  box-shadow: 2px 2px 20px 1px rgb(0 0 0 / 40%);
  overflow-x: hidden;
  display: flex;
  flex-direction: column;
  width: 50%;
  height: 80%;
  border-radius: 3px;
}

.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
}

.modal-header {
  position: relative;
  border-bottom: 1px solid #eeeeee;
  color: #000000;
  justify-content: space-between;
}

.modal-footer {
  border-top: 1px solid #eeeeee;
  flex-direction: column;
  justify-content: flex-end;
}

.modal-body {
  position: relative;
  padding: 10px 10px;
}

.btn-close {
  position: absolute;
  top: 0;
  right: 0;
  border: none;
  font-size: 20px;
  padding: 10px;
  margin-right: 10px;
  margin-top: 4px;
  cursor: pointer;
  font-weight: bold;
  color: #000000;
  background: transparent;
}

</style>