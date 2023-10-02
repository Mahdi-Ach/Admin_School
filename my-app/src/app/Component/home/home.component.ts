import { AfterViewInit, Component, OnInit } from '@angular/core';
import {faSearch,faBars, faBookReader,faHome, faUser,faUsers,faClipboard,faUserPlus,faAngleUp,faDownload,faBell,faEnvelope } from '@fortawesome/free-solid-svg-icons';
import * as $ from 'jquery'
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements AfterViewInit  {
  BookReaderIcon = faBookReader;
  HomeIcon = faHome;
  UserIcon = faUser;
  UsersIcon = faUsers;
  ClipBoard = faClipboard;
  UserPlusIcon = faUserPlus;
  AngleUpIcon = faAngleUp;
  DownloadIcon = faDownload;
  faBell = faBell;
  faEnvelope=faEnvelope;
  faBars = faBars;
  isNavbarCollapsed=true;
  faSearch = faSearch;
  ngAfterViewInit(): void {
    $(function(){
      $(".sidebar_option").on("click",function(){
        if (window.innerWidth <= 768) {
          $(".accordition_items").not($(this).next()).slideUp(0);
          $(this).next().slideToggle(0);
        } else {
            $(".sidebar_option fa-icon.down").not($(this).children("fa-icon.down")).removeClass("down")
            $(this).children("fa-icon").toggleClass("down")
            $(".accordition_items").not($(this).next()).slideUp(500);
            $(this).next().slideToggle(500);
        }
      })
    })
  }
  ngOnInit(): void {
      
  }
}
