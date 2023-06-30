<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h4 class="pt-3">Wishlist</h4>
      </div>
    </div>

    <div class="row">
      <div
        v-for="product of products"
        :key="product.id"
        class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex"
      >
        <Product-box :product="product"></Product-box>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ProductBox from "@/components/Product/ProductBox.vue";
export default {
  data() {
    return {
      token: null,
      products: null,
    };
  },
  components: { ProductBox },
  props: ["baseURL"],
  methods: {
    fetchWishList() {
      axios
        .get(`${this.baseURL}wishlist/${this.token}`)
        .then((data) => {
          this.products = data.data;
        })
        .catch((err) => console.log("err", err));
    },
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.fetchWishList();
  },
};
</script>