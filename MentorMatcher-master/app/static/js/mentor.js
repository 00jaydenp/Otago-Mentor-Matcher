/* global Vue, axios */
"use strict";
var registerMentorApi = '/api/register/mentor';
var mentorApi = '/api/mentors';
var mentorIDApi = ({mentor_ID}) => `/api/mentors/${mentor_ID}`;
var registerPersonAPI = '/api/user';
var personIDApi = ({person_ID}) => `/api/login/${person_ID}`;
var matchApi = 'api/match';
var matchIDApi = ({match_ID}) => `api/match/${match_ID}`;
var matchMentorIDApi = ({mentor_ID}) => `/api/mentor/match/${mentor_ID}`;
var menteeIDApi = ({mentee_ID}) => `/api/mentees/${mentee_ID}`;
var workMentorIDAPI = ({mentor_ID}) => `api/work/${mentor_ID}`;
var undergradPersonIDAPI = ({person_ID}) => `api/undergraduate/${person_ID}`;
var postgradPersonIDAPI = ({person_ID}) => `api/postgraduate/${person_ID}`;

const app = Vue.createApp({

    data() {
        return {
            workExperiences: new Array(),

            mentor: new Object({
                currentTitle: '',
                currentOrganisation: '',
                mentoringPreference: new Array(),
                person: new Object({
                    postgrad: new Array(),
                    undergrad: new Array()
                })
            }),

            mentors: new Array(),

            match: new Object({
                mentor: new Object({
                    person: new Object()
                }),
                mentee: new Object({
                    person: new Object()
                })
            }),

            undergrads: new Array(),

            postgrads: new Array(),

            matches: new Array(),

            pendingMatches: new Array(),

            adminPendingMatches: new Array(),

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
        if (document.URL.includes("viewmentors.html")) {
            this.getMentors();
        } else if (document.URL.includes("viewmentormatches.html")) {
            this.checkMentor();
            this.getMatch();
        } else if (document.URL.includes("viewpendingmentormatches.html")) {
            this.checkMentor();
            this.getMatch();
        } else if (document.URL.includes("adminviewpendingmatches.html")) {
            this.checkAdmin();
            this.getAdminMatches();
        } else if (document.URL.includes("mentor.html")) {
            this.getUndergrad(this.selectedMentor.personID);
            this.getPostgrad(this.selectedMentor.personID);
            this.getWork(this.selectedMentor.mentorID);
        }
    },

    methods: {
        /*
         * Registers a Mentor user in the system, and redirects to undergraduate degree registration page.
         */
        registerMentor() {
            this.mentor.person = this.personUser;
            axios.get(mentorApi)
                    .then(response => {
                        var mentorID = response.data.length + 1;
                        this.mentor.mentorID = mentorID;
                        dataStore.commit("mentorUser", this.mentor);
                        axios.post(registerMentorApi, this.mentor)
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
         * Retreives all mentors in the system and their relevant information to be displayed in the view mentors page. 
         */
        getMentors() {
            axios.get(mentorApi)
                    .then(response => {
                        for (let i = 0; i < response.data.length; i++) {
                            axios.get(personIDApi({'person_ID': response.data[i].personID}))
                                    .then(result => {
                                        axios.get(workMentorIDAPI({'mentor_ID': response.data[i].mentorID}))
                                                .then(res => {
                                                    console.log(res);
                                                    if (result.data.active) {
                                                        this.mentor = response.data[i];
                                                        if (res.data.length > 0) {
                                                            this.mentor.currentTitle = res.data[0].title;
                                                            this.mentor.currentOrganisation = res.data[0].organisation;
                                                        }
                                                        this.mentor.person = result.data;
                                                        this.mentors.push(this.mentor);

                                                    }
                                                });
                                    });
                        }
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occured - check the console for details");
                    });

        },

        /*
         * IS THIS USED????
         * Retrieves the current occupation information of a mentor.
         * @param mentorID identifies the mentor who's current occupation is being retrieved.
         */
        getCurrentWork(mentorID) {
            axios.get(workMentorIDAPI({'mentor_ID': mentorID}))
                    .then(response => {
                        alert(response.data[0].organisation);
                        this.mentor.currentTitle = response.data[0].title;
                        this.mentor.currentOrganisation = response.data[0].organisation;

                    });
        },

        /*
         * Retrieves the undergraduate degree information of a user.
         * @param personID the user who's undergraduate degree information is being retrieved.
         */
        getUndergrad(personID) {
            axios.get(undergradPersonIDAPI({'person_ID': personID}))
                    .then(response => {
                        this.undergrads = response.data;

                    });
        },

        /*
         * Retrieves the postgraduate degree information of a user.
         * @param personID the user who's postgraduate degree information is being retrieved.
         */
        getPostgrad(personID) {
            axios.get(postgradPersonIDAPI({'person_ID': personID}))
                    .then(response => {
                        this.postgrads = response.data;

                    });
        },

        /*
         * Retrieves the work experience information of a mentor.
         * @param mentorID identifies the mentor who's work experience is being retrieved.
         */
        getWork(mentorID) {
            axios.get(workMentorIDAPI({'mentor_ID': mentorID}))
                    .then(response => {
                        this.workExperiences = response.data;

                    });
        },

        /*
         * Ensures pages with mentor only access are not visible by other users.
         */
        checkMentor() {
            if (this.mentorUser === null && this.adminUser === null) {
                alert("Invalid permissions to see this page");
                window.location = "home.html";
            }
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
         * CANNOT SEE WHERE THIS IS ACTUALLY USED??
         */
        getMentor(mentorID) {
            axios.get(mentorIDApi({'mentor_ID': mentorID}))
                    .then(response => {
                        axios.get(personIDApi({'person_ID': response.data.personID}))
                                .then(result => {
                                    this.mentor = response.data;
                                    this.mentor.person = result.data;
                                });
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        },

        /*
         * Allows a mentee to request a match with a mentor. The mentee is relocated to the home page. 
         */
        registerApplication() {
            axios.get(matchApi)
                    .then(response => {
                        this.match.matchID = response.data.length + 1;
                        this.match.mentor = this.selectedMentor;
                        this.match.mentee = this.menteeUser;
                        axios.post(matchApi, this.match)
                                .then(() => {
                                    window.location = 'home.html';
                                    alert("Success");
                                })
                                .catch(error => {
                                    console.error(error);
                                    alert("You Have Already Applied to this Mentor");
                                });
                    });

        },

        /*
         * Adds mentor and mentee information to the match object, which is added to the corresponding array dependant 
         * on which stage in the match approval process it is in. 
         */
        getMatch() {
            axios.get(matchMentorIDApi({'mentor_ID': this.mentorUser.mentorID}))
                    .then(response => {
                        this.matches = response.data;
                        Array.from(this.matches).forEach(match => {
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
                            if (match.approved === false) {
                                this.pendingMatches.push(match);
                            } else if (match.approved === true && match.adminApproved === true) {
                                this.confirmedMatches.push(match);
                            }
                        });
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        },

        /*
         * Adds mentor and mentee information to the match object, which is added to the array of match objects
         * awaiting admin approval.
         */
        getAdminMatches() {
            axios.get(matchApi)
                    .then(response => {
                        this.matches = response.data;
                        this.matches.forEach(match => {
                            if (match.approved === true && match.adminApproved === false) {
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
                                this.adminPendingMatches.push(match);
                            }
                        });
                    });

        },

        /*
         * Allows a mentor to approve mentee-requested match(es).
         * @param matchID the ID of the requested match to be approved.
         */
        approveMatch(matchID) {
            axios.get(matchIDApi({'match_ID': matchID}))
                    .then(response => {
                        this.match = response.data;
                        this.match.approved = true;
                        axios.put(matchIDApi({'match_ID': this.match.matchID}), this.match)
                                .then(() => {
                                    window.location = "";
                                });
                    });
        },

        /*
         * Allows a mentor to decline mentee-requested match(es). Match will be deleted from the database.
         * @param matchID the ID of the requested match to be declined.
         */
        declineMatch(matchID) {
            axios.delete(matchIDApi({'match_ID': matchID}))
                    .then(response => {
                        window.location = "";
                    })
        },

        /*
         * Allows an Admin to approve requested match(es) that have been approved by the mentor of that match.
         * @param matchID the ID of the mentor-approved match to be approved by Admin.
         */
        adminApproveMatch(matchID) {
            axios.get(matchIDApi({'match_ID': matchID}))
                    .then(response => {
                        this.match = response.data;
                        this.match.adminApproved = true;
                        axios.put(matchIDApi({'match_ID': this.match.matchID}), this.match)
                                .then(() => {
                                    window.location = "";
                                });
                    });
        },

        /*
         * Allows an Admin to decline requested match(es) that have been approved by the mentor of that match.
         * Match will be deleted from the database.
         * @param matchID the ID of the mentor-approved match to be declined by Admin.
         */
        adminDeclineMatch(matchID) {
            //delete record from the database
            axios.delete(matchIDApi({'match_ID': matchID}))
                    .then(response => {
                        window.location = "";
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
