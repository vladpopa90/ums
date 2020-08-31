import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})

export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): true | UrlTree {
    const url: string = state.url;

    return this.checkLogin();
  }

  checkLogin(): true | UrlTree {
    if (this.authService.isAuthenticated()) { return true; }

    // Redirect to the login page
    return this.router.parseUrl('/login');
  }
}