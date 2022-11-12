/* global axios, Vue */

"use strict";
var registerMenteeApi = '/api/register/mentee';
var menteeApi = '/api/mentees';
var menteeIDApi = ({mentee_ID}) => `/api/mentees/${mentee_ID}`;
var registerPersonAPI = '/api/user';
var personIDApi = ({person_ID}) => `/api/login/${person_ID}`;
var mentorIDApi = ({mentor_ID}) => `/api/mentors/${mentor_ID}`;
var matchMenteeIDApi = ({mentee_ID}) => `/api/mentee/match/${mentee_ID}`;
const app = Vue.createApp({

    data() {
        return {

            mentee: new Object({
                person: new Object()
            }),

            matches: new Array(),

            confirmedMatches: new Array()
        };
    },
    computed: Vuex.mapState({
        personUser: 'personUser',
        selectedMentor: 'selectedMentor',
        menteeUser: 'menteeUser',
        mentorUser: 'mentorUser'

    }),

    mounted() {
        if (document.URL.includes("viewmenteematches.html")) {
            this.getConfirmedMatches(this.menteeUser.menteeID);
        }
    },

    methods: {
        /*
         * Registers a Mentee user in the system, and redirects to undergraduate degree registration page.
         */
        registerMentee() {
            this.mentee.person = this.personUser;
            axios.get(menteeApi)
                    .then(response => {
                        var menteeID = response.data.length + 1;
                        this.mentee.menteeID = menteeID;
                        dataStore.commit("menteeUser", this.mentee);
                        axios.post(registerMenteeApi, this.mentee)
                                .then(() => {
                                    window.location = 'registerundergrad.html';
                                })
                                .catch(error => {
                                    console.error(error);
                                    alert("An error occurred - check the console for details.");
                                });
                    });

        },

        /*
         * NECESSARY???
         */
        getMatch(menteeID) {
            axios.get(matchMenteeIDApi({'mentee_ID': menteeID}))
                    .then(response => {
                        this.matches = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        },

        /*
         * Adds matches that meet approval criteria to confirmedMatches array to be displayed in a 
         * mentee's confirmed matches page, along with relevant mentor object information.
         */
        getConfirmedMatches() {
            axios.get(matchMenteeIDApi({'mentee_ID': this.menteeUser.menteeID}))
                    .then(response => {
                        this.matches = response.data;
                        Array.from(this.matches).forEach(match => {
                            if (match.approved === true && match.adminApproved === true) {
                                this.match = match;
                                axios.get(mentorIDApi({'mentor_ID': match.mentorID}))
                                        .then(result => {
                                            this.match.mentor = result.data;

                                            axios.get(personIDApi({'person_ID': result.data.personID}))
                                                    .then(response => {


                                                        this.match.mentor.person = response.data;
                                                    });
                                        })
                                        .catch(error => {
                                            console.error(error);
                                            alert("An error occured - check the console for details");
                                        });
                                axios.get(menteeIDApi({'mentee_ID': match.menteeID}))
                                        .then(result => {
                                            this.match.mentee = result.data;

                                            axios.get(personIDApi({'person_ID': result.data.personID}))
                                                    .then(response => {


                                                        this.match.mentee.person = response.data;
                                                    });
                                        })
                                this.confirmedMatches.push(match);
                            }
                        });
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        }

    }

});


// import data store
import { dataStore } from './data-store.js'
        app.use(dataStore);

import { NavigationMenu } from './navigation.js';
app.component('navigation', NavigationMenu);
// mount the page - this needs to be the last line in the file
app.mount("main");