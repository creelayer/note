<template>
  <textarea class="textarea autosize h3" ref="autosize" :value='modelValue' @input='$emit("update:modelValue", $event.target.value)'/>
</template>

<style scoped>
.autosize{
  width: 100%;
  border: none;
}
</style>

<script>
export default {
  name: "TextareaAutoSize",
  props: {
    modelValue: String
  },
  created() {
    this.$nextTick(() => {
      this.$refs.autosize.addEventListener("input", this._resize);
    });
  },
  methods: {
    _resize(event) {
      event.target.style.height = "auto";
      event.target.style.height = `${event.target.scrollHeight}px`;
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.$refs.autosize.style.height = `${this.$refs.autosize.scrollHeight}px`;
    });
  },
}
</script>

<style scoped>

</style>