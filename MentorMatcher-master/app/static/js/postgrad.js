/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

/* global Vue, axios, Vuex */
"use strict";
var postgradAPI = '/api/postgraduate';
var postgradPersonIDAPI = ({person_ID}) => `api/postgraduate/${person_ID}`;

const app = Vue.createApp({
    data() {
        return{
            postgrad: new Object({
                person: new Object()
            }),

            postgraduates: new Array()
        };
    },

    computed: Vuex.mapState({
        personUser: 'personUser'
    }),

    mounted() {
        this.getPostgrads();
        this.getPostgradByID(this.personUser.personID);
    },

    methods: {
        /*
         * Allows a user to register their postgraduate degree information. 
         */
        registerPostgrad() {
            axios.post(postgradAPI, this.postgrad)
                    .then(() => {
                        window.location = 'registerpostgrad.html';
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });
        },

        /*
         * Redirects user to correct page within the registration process dependent on their role.
         */
        next() {
            if (this.personUser.role === "Mentor") {
                window.location = 'registerwork.html';
            } else if (this.personUser.role === "Mentee") {
                window.location = 'home.html';
            }
        },

        /*
         * Assigns a postgradExperienceID to a postgrad by retrieving the number of postgrad entities in the system.
         */
        getPostgrads() {
            this.postgrad.person = this.personUser;
            axios.get(postgradAPI)
                    .then(response => {
                        this.postgrad.postgradExperienceID = response.data.length + 1;
                    });
        },

        /*
         * Retrieves all postgrad entries for a given user.
         * @param personID the user who's postgrad info is being retrieved.
         */
        getPostgradByID(personID) {
            axios.get(postgradPersonIDAPI({'person_ID': personID}))
                    .then(response => {
                        this.postgraduates = response.data;
                    });
        }
    }

});

// other component imports go here

// import data store
import { dataStore } from './data-store.js'
        app.use(dataStore);

// mount the page - this needs to be the last line in the file
app.mount("main");

