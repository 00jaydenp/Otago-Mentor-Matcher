/* global Vue, axios */
"use strict";
const app = Vue.createApp({

    data() {

    },

    computed: Vuex.mapState({
        menteeUser: 'menteeUser',
        mentorUser: 'mentorUser',
        personUser: 'personUser'

    }),

    mounted() {

    },
    methods: {
    }

});


// import data store
import { dataStore } from './data-store.js'
        app.use(dataStore);

import { NavigationMenu } from './navigation.js';
app.component('navigation', NavigationMenu);

app.mount("main");
