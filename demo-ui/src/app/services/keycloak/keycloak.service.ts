import {Injectable} from '@angular/core';
import Keycloak, {KeycloakInstance} from "keycloak-js";
import {UserProfile} from "./user-profile";

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  private _keycloak: KeycloakInstance | undefined;
  private _profile: UserProfile | undefined;

  get keycloak(){
    if (!this._keycloak){
      this._keycloak = new Keycloak({
        url: 'http://localhost:9090',
        realm: 'edu-system',
        clientId: 'edu-sys'
      })
    }
    return this._keycloak
  }

  get profile(): UserProfile | undefined {
    return this._profile
  }

  constructor() { }

 async init() {
    const authenticated = await this.keycloak?.init({
      onLoad: 'login-required'
    })

   if (authenticated){
     this._profile = (await this.keycloak?.loadUserProfile()) as UserProfile
     this._profile.token = this.keycloak?.token || ''
   }
  }

  login() {
    // Vérification que keycloak est défini avant d'appeler la méthode
    return this.keycloak?.login()
  }

  logout() {
    // return  this.keycloak.accountManagement();
    // Vérification que keycloak est défini avant d'appeler la méthode
    return this.keycloak?.logout({ redirectUri: 'http://localhost:4200' });
  }
}
