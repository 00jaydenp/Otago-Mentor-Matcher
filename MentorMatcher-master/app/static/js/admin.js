/* global Vue, axios */
"use strict";
var registerMentorApi = '/api/register/mentor';
var mentorApi = '/api/mentors';
var mentorIDApi = ({mentor_ID}) => `/api/mentors/${mentor_ID}`;
var menteeApi = '/api/mentees';
var menteeIDApi = ({mentee_ID}) => `/api/mentees/${mentee_ID}`;
var personIDApi = ({person_ID}) => `/api/login/${person_ID}`;
var matchApi = 'api/match';
var matchIDApi = ({match_ID}) => `api/match/${match_ID}`;
var matchMentorIDApi = ({mentor_ID}) => `/api/mentor/match/${mentor_ID}`;
const app = Vue.createApp({

    data() {
        return {
// models (comma separated key/value pairs)

            person: new Object(),

            match: new Object({
                mentor: new Object({
                    person: new Object()
                }),
                mentee: new Object({
                    person: new Object()
                })
            }),
            mentee: new Object({
                person: new Object()
            }),
            mentor: new Object({
                person: new Object()
            }),
            matches: new Array(),
            mentees: new Array(),
            mentors: new Array(),
            confirmedMatches: new Array(),
            signInMessage: "Please sign in to continue."
        };
    },
    computed: Vuex.mapState({
        selectedMentor: 'selectedMentor',
        personUser: 'personUser',
        menteeUser: 'menteeUser',
        mentorUser: 'mentorUser',
        adminUser: 'adminUser'

    }),
    mounted() {
        this.checkAdmin();
        if (document.URL.includes("adminviewusers.html")) {
            this.getMentors();
            this.getMentees();
            this.getConfirmedMatches();
        }
    },
    methods: {
        /*
         * Gets all mentors in the system and adds them to the mentors array.
         */
        getMentors() {
            axios.get(mentorApi)
                    .then(response => {
                        for (let i = 0; i < response.data.length; i++) {
                            axios.get(personIDApi({'person_ID': response.data[i].personID}))
                                    .then(result => {
                                        if (result.data.active) {
                                            this.mentor = response.data[i];
                                            this.mentor.person = result.data;
                                            this.mentors.push(this.mentor);
                                        }
                                    });
                        }
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occured - check the console for details");
                    });
        },

        /*
         * Gets all mentees in the system and adds them to the mentees array.
         */
        getMentees() {
            axios.get(menteeApi)
                    .then(response => {
                        for (let i = 0; i < response.data.length; i++) {
                            axios.get(personIDApi({'person_ID': response.data[i].personID}))
                                    .then(result => {
                                        if (result.data.active) {
                                            this.mentee = response.data[i];
                                            this.mentee.person = result.data;
                                            this.mentees.push(this.mentee);
                                        }
                                    });
                        }
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occured - check the console for details");
                    });
        },

        /*
         * Deletes a user from the database.
         */
        deleteUser(user) {
            if (user.person.role === "Mentor" || user.person.role === "Admin") {
                this.person = user.person;
                this.person.active = false;
                axios.put(personIDApi({'person_ID': user.person.personID}), this.person)
                        .then(() => {
                            alert("Successfully Removed Mentor");
                            location.reload();
                        })
                        .catch(error => {
                            console.error(error);
                            alert("This mentor has an existing match, please remove the match first.");
                        });
            } else if (user.person.role === "Mentee") {
                this.person.active = false;
                axios.put(personIDApi({'person_ID': user.person.personID}), this.person)
                        .then(() => {
                            alert("Successfully Removed Mentee");
                            location.reload();
                        })
                        .catch(error => {
                            console.error(error);
                            alert("Error: Check Console for Details");
                        });

            }

        },

        adminAccess(mentor) {
            axios.get(personIDApi({'person_ID': mentor.person.personID}))
                    .then(response => {
                        this.person = response.data;
                        this.person.role = "Admin";
                        axios.put(personIDApi({'person_ID': this.person.personID}), this.person)
                                .then(() => {
                                    location.reload();
                                });
                    });

        },

        /*
         * Deletes a match from the database.
         */
        deleteMatch(matchID) {
            axios.delete(matchIDApi({'match_ID': matchID}))
                    .then(() => {
                        alert("Successfully removed match with match id " + matchID);
                        window.location = "adminviewusers.html";
                    })
                    .catch(error => {
                        console.error(error);
                        alert("This mentor has an existing match, please remove the match first.");
                    });
        },

        /*
         * Ensures pages with admin only access are not visible by other users.
         */
        checkAdmin() {
            if (this.adminUser == null) {
                alert("Invalid permissions to see this page");
                window.location = "home.html";
            }
        },

        /*
         * Relocates to mentor page that displays information of the selected mentor.
         * @param mentor the selected mentor to be displayed.
         */
        selectMentor(mentor) {
            dataStore.commit("selectMentor", mentor);
            window.location = "mentor.html";
        },

        /*
         * Displays all existing confirmed matches to admin for visibility.
         */
        getConfirmedMatches() {
            axios.get(matchApi)
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
                                        .catch(error => {
                                            console.error(error);
                                            alert("An error occured - check the console for details");
                                        });
                                this.confirmedMatches.push(this.match);
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

app.mount("main");
