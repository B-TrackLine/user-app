/**
 * Fitrockr User Service
 *
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { Language } from './language';
import { Country } from './country';


export interface UserCreateModel { 
    name: string;
    email: string;
    country: Country;
    language: Language;
}
export namespace UserCreateModel {
}


