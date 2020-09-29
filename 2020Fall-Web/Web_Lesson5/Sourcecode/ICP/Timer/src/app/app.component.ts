import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  // global variable
  startTimer = 0;
  // functions
  calcTime(dates) {
    const newYear = new Date('1.1.2020').getTime();
    let date;
    clearInterval(this.startTimer); // terminate timer
    if (typeof(dates) === 'undefined') {
      date = new Date(newYear).getTime();
    } else {
      date = new Date(dates).getTime();
    }
    console.log(date);
    this.updateTimer(date) ; // declaration is below
  }
  updateTimer(date) {
    this.startTimer = setInterval(() => { // execute every one second
      const now = new Date().getTime();
      const distance = date - now;
      const days = Math.floor(distance / (1000 * 60 * 60 * 24));
      const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
      const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
      const seconds = Math.floor((distance % (1000 * 60)) / 1000);
      document.querySelector('.clock-day').innerHTML = '' + days;
      document.querySelector('.clock-hours').innerHTML = '' + hours;
      document.querySelector('.clock-minutes').innerHTML = '' + minutes;
      document.querySelector('.clock-seconds').innerHTML = '' + seconds;

      if (now >= date) {// terminate timer
        clearInterval(this.startTimer); // terminate timer
        document.querySelector('.clock-day').innerHTML = 'D';
        document.querySelector('.clock-hours').innerHTML = 'O';
        document.querySelector('.clock-minutes').innerHTML = 'N';
        document.querySelector('.clock-seconds').innerHTML = 'E';
      }
  }, 1000);
  }
  }
