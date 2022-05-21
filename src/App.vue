<template>
  <nav class="navbar navbar-light" style="background-color: #1c8e3e">
    <a class="navbar-brand" href="#">
      <img
        src="https://iconape.com/wp-content/files/cz/184419/svg/184419.svg"
        width="50"
        height="50"
        class="d-inline-block align-top"
        alt=""
      />
      <router-link to="/" style="text-decoration: none; color: inherit; margin: 15px; font-size: 1.0rem; position: ">Transferência </router-link> 
      <router-link to="/lista" style="text-decoration: none; color: inherit; margin: 15px; font-size: 1.0rem; top: 50%;">Lista</router-link>
    </a>
  </nav>
  <router-view />
</template>

<style>
</style>
