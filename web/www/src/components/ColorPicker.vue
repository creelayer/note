<template>
  <div v-bind:class="show?'show':''">
    <div :key="index" v-for="(color,index) in colors" v-bind:class="'dot '+(color === current ? ' current':'')"
         v-bind:style="'background-color:#'+color" v-on:click="choose(color)"
    >
    </div>
  </div>
</template>


<style>

.dot {
  float: left;
  width: 18px;
  height: 18px;
  border-radius: 10px;
  cursor: pointer;
  margin-right: 4px;
  display: none;
  transition: opacity 1s ease-out;
  opacity: 0;
}

.show .dot {
  opacity: 1;
  display: block;
}

.current {
  color: #FFFFFF;
  line-height: 18px;
  font-weight: bold;
  padding-left: 1px;
  display: block;
  opacity: 1;
}

</style>

<script>
export default {
  name: "ColorPicker",
  props: {
    identity: null,
    afterChoose: null,
    currentColor: null
  },
  created() {
    this.current = this.currentColor
  },
  data() {
    return {
      show: false,
      colors: ['2ECC71',  'FFC300', 'FF5733','DE3163','DFFF00','40E0D0','CCCCFF','6495ED'],
      current: null
    }
  },
  methods: {
    choose: function (color) {

      if (this.afterChoose && color !== this.current) {
        this.afterChoose(this.identity, color)
      }

      if (this.show) {
        this.current = color;
      }

      this.show = !this.show;
    }
  }
}
</script>

<style scoped>

</style>