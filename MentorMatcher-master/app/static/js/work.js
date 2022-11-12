/* global Vue, axios */
"use strict";
var workAPI = '/api/work';
var workMentorIDAPI = ({mentor_ID}) => `api/work/${mentor_ID}`;

const app = Vue.createApp({
    data() {
        return{
            work: new Object({
                mentor: new Object()
            }),

            works: new Array()
        };
    },

    computed: Vuex.mapState({
        mentorUser: 'mentorUser'
    }),

    mounted() {
        this.getWorks();
        this.getWorksByID(this.mentorUser.mentorID);
    },

    methods: {
        /*
         * Allows a user to register their work experience information. 
         */
        registerWork() {
            axios.post(workAPI, this.work)
                    .then(() => {
                        window.location = 'registerwork.html';
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });
        },

        /*
         * redirects the user to the home screen once registration is complete.
         */
        next() {
            window.location = 'home.html';
        },

        /*
         * Assigns a workID to a work object by retrieving the number of work experiences objects there are in the system.
         */
        getWorks() {
            this.work.mentor = this.mentorUser;
            axios.get(workAPI)
                    .then(response => {
                        this.work.workID = response.data.length + 1;
                    });
        },

        /*
         * Retrieves work experience entries for a given mentor.
         * @param mentorID the mentor who's work experience info is being retrieved.
         */
        getWorksByID(mentorID) {
            axios.get(workMentorIDAPI({'mentor_ID': mentorID}))
                    .then(response => {
                        this.works = response.data;
                    });
        }
    }

});


// import data store
import { dataStore } from './data-store.js'
        app.use(dataStore);


app.mount("main");