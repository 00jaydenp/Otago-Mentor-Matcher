"use strict";

// import data store
import { dataStore } from './data-store.js'

export const NavigationMenu = {

    computed: {

        menteeSignedIn() {
            return this.menteeUser != null;
        },

        mentorSignedIn() {
            return this.mentorUser != null;
        },

        adminRole() {
            if (this.mentorUser != null) {
                if (this.mentorUser.person.role === "Admin") {
                    return true;
                }
            }

        },

        ...Vuex.mapState({
                menteeUser: 'menteeUser',
                mentorUser: 'mentorUser',
                adminUser: 'adminUser'
        })
    },

    template:
            `
    <nav>
        <div class='topBar'> 
            <h1 class='topBarText'>Otago Mentor Match</h1>
        </div>

        <div class='nav'>  
            <a href="home.html">Home</a>&nbsp
            <a href="viewpendingmentormatches.html" v-if="mentorSignedIn">My Pending Matches</a>&nbsp
            <a href="viewmentormatches.html" v-if="mentorSignedIn">My Confirmed Matches</a>&nbsp
            <a href="admindashboard.html" v-if="adminRole">Admin Dashboard</a>&nbsp
            <a href="viewmenteematches.html" v-if="menteeSignedIn">My Matches</a>&nbsp
            <a href="viewmentors.html" v-if="menteeSignedIn">View Mentors</a>&nbsp 
            <a href="#" v-if="menteeSignedIn || mentorSignedIn" @click="signOut()" class="navButton">Sign Out</a>&nbsp
        </div>
    </nav>
    `,

    methods: {
        signOut() {
            sessionStorage.clear();
            window.location = '.';
        }

    }

}; 