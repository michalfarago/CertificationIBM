<template>
  <v-container fluid fill-height>
    <v-layout align-center justify-center>
      <div class="home">
        <v-row>
          <v-col cols="12">
            <div
              class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200 mt-3"
            >
              <h1 class="text-center">Certification Application</h1>
            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12">
            <v-layout align-center justify-center>
              <ol class="odd">
                <li class="hex">
                  <div class="hex-content">
                    <router-link
                      to="/certificationsoverview"
                      tag="span"
                      class="pointerClass"
                    >
                      <h2 class="text-xl">Certifications</h2>
                    </router-link>
                  </div>
                </li>
                <li class="hex">
                  <div class="hex-content">
                    <router-link to="/voucher" tag="span" class="pointerClass">
                      <h2 class="text-xl">Vouchers</h2>
                    </router-link>
                  </div>
                </li>
                <li class="hex">
                  <div class="hex-content">
                    <router-link
                      to="/skillsoverview"
                      tag="span"
                      class="pointerClass"
                    >
                      <h2 class="text-xl">Skills</h2>
                    </router-link>
                  </div>
                </li>
                <li class="hex">
                  <div class="hex-content">
                    <router-link to="/profile" tag="span" class="pointerClass">
                      <span>Profile</span>
                    </router-link>
                  </div>
                </li>
              </ol>
            </v-layout>
          </v-col>
        </v-row>
      </div>
    </v-layout>
  </v-container>
</template>

<style>
ol.even {
  position: relative;
  left: 5.45455rem;
}

ol.odd {
  position: relative;
  margin-bottom: -4.5%;
}

.hex {
  position: relative;
  margin: 1em auto;
  width: 6rem;
  height: 10.2rem;
  color: white;
  background: #679b7c;
  transform: rotate(-90deg);
  display: inline-block;
  list-style-type: none;
  margin-right: 4.61538rem;
  transition: all 150ms ease-in-out;
  z-index: 2;
}

.hex:before,
.hex:after {
  position: absolute;
  width: inherit;
  height: inherit;
  border-radius: inherit;
  background: inherit;
  content: "";
  z-index: -1;
}

.hex:before {
  transform: rotate(60deg);
}

.hex:after {
  transform: rotate(-60deg);
}

.hex:hover {
  background: #1c443d;
  color: black;
  cursor: pointer;
}
.hex .hex-content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%) rotate(90deg);
  color: white;
}
</style>
