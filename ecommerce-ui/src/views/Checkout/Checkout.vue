<template>
  <div class="div_class">
    <h3>You will be redirected to payment page</h3>
    <div class="alert alert-primary">
      While making payment user card number 4242 4242 4242 4242 and enter random
      date and cvv(3 digit)
    </div>
    <button class="btn btn-primary" @click="goToCheckout">Make payment</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      stripeAPIToken:
        "pk_test_51NGRRVIJQwDgZsoFT9OeIwyYY9oiacBR75LPj2kNms26jGkoFb6FTpu0Qu09KhxPmDnfazp63jzk1Qzw1awUkNt500lVKo3oSe",
      stripe: "",
      token: null,
      checkoutBodyArray: [],
    };
  },
  name: "Checkout",
  props: ["baseURL"],
  methods: {
    getAllItem() {
      axios;
      axios
        .get(`${this.baseURL}cart/?token=${this.token}`)
        .then((response) => {
          if (response.status == 200) {
            let products = response.data;
            for (let i = 0; i < products.cartItems.length; i++) {
              this.checkoutBodyArray.push({
                price: products.cartItems[i].product.price,
                quantity: products.cartItems[i].quantity,
                productName: products.cartItems[i].product.name,
                productId: products.cartItems[i].product.id,
              });
            }
          }
        })
        .catch((err) => console.log(err));
    },

    goToCheckout() {
      console.log("checkoutarray", this.checkoutBodyArray);
      axios
        .post(
          `${this.baseURL}order/create-checkout-session`,
          this.checkoutBodyArray
        )
        .then((res) => {
          localStorage.setItem("sessionId", res.data.sessionId);
          console.log("session", res.data);
          this.stripe.redirectToCheckout({ sessionId: res.data.sessionId });
        });
    },
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.stripe = window.Stripe(this.stripeAPIToken);
    this.getAllItem();
  },
};
</script>

<style scoped>
.alert {
  width: 50%;
}

.div_class {
  margin-top: 5%;
  margin-left: 30%;
}

.checkout_button {
  background-color: #5d3dec;
  margin: 10px;
}
</style>