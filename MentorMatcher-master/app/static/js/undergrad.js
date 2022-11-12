/* global Vue, axios */
"use strict";
var undergradAPI = '/api/undergraduate';
var undergradPersonIDAPI = ({person_ID}) => `api/undergraduate/${person_ID}`;

const app = Vue.createApp({
    data() {
        return{
            undergrad: new Object({
                person: new Object()
            }),

            undergraduates: new Array()
        };
    },

    computed: Vuex.mapState({
        personUser: 'personUser'
    }),

    mounted() {
        this.getUndergrads();
        this.getUndergradByID(this.personUser.personID);
    },

    methods: {
        /*
         * Allows a user to register their undergraduate degree information. 
         */
        registerUndergrad() {
            axios.post(undergradAPI, this.undergrad)
                    .then(() => {
                        window.location = 'registerundergrad.html';
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
            window.location = 'registerpostgrad.html';
        },

        /*
         * Assigns a undergradExperienceID to a undergrad by retrieving the number of undergrad entities in the system.
         */
        getUndergrads() {
            this.undergrad.person = this.personUser;
            axios.get(undergradAPI)
                    .then(response => {
                        this.undergrad.undergradExperienceID = response.data.length + 1;
                    });
        },

        /*
         * Retrieves all undergrad entries for a given user.
         * @param personID the user who's undergrad info is being retrieved.
         */
        getUndergradByID(personID) {
            axios.get(undergradPersonIDAPI({'person_ID': personID}))
                    .then(response => {
                        this.undergraduates = response.data;
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
