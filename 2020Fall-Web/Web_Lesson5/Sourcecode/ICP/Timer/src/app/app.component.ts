import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  calcTime(dates) {
    const newYear = new Date('1.1.2020').getTime();
    let date;
    if (typeof(dates) === 'undefined') {
      date = new Date(newYear).getTime();
    } else {
      date = new Date(dates).getTime();
    }
    return date;
  }
  updateTimer(date) {
    const now = new Date().getTime();
    const distance = date - now;
    const days = Math.floor(distance / (1000 * 60 * 60 * 24));
    const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((distance % (1000 * 60)) / 1000);

    /*document.querySelector('.clock-day').innerHTML = days;
    document.querySelector('.clock-hours').innerHTML = hours;
    document.querySelector('.clock-minutes').innerHTML = minutes;
    document.querySelector('.clock-seconds').innerHTML = seconds;*/

    if (now >= date) {
      document.querySelector('.clock-day').innerHTML = 'D';
      document.querySelector('.clock-hours').innerHTML = 'O';
      document.querySelector('.clock-minutes').innerHTML = 'N';
      document.querySelector('.clock-seconds').innerHTML = 'E';
    }
  }
  }







