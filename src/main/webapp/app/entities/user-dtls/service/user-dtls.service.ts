import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUserDtls, NewUserDtls } from '../user-dtls.model';

export type PartialUpdateUserDtls = Partial<IUserDtls> & Pick<IUserDtls, 'id'>;

export type EntityResponseType = HttpResponse<IUserDtls>;
export type EntityArrayResponseType = HttpResponse<IUserDtls[]>;

@Injectable({ providedIn: 'root' })
export class UserDtlsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/user-dtls');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(userDtls: NewUserDtls): Observable<EntityResponseType> {
    return this.http.post<IUserDtls>(this.resourceUrl, userDtls, { observe: 'response' });
  }

  update(userDtls: IUserDtls): Observable<EntityResponseType> {
    return this.http.put<IUserDtls>(`${this.resourceUrl}/${this.getUserDtlsIdentifier(userDtls)}`, userDtls, { observe: 'response' });
  }

  partialUpdate(userDtls: PartialUpdateUserDtls): Observable<EntityResponseType> {
    return this.http.patch<IUserDtls>(`${this.resourceUrl}/${this.getUserDtlsIdentifier(userDtls)}`, userDtls, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserDtls>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserDtls[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getUserDtlsIdentifier(userDtls: Pick<IUserDtls, 'id'>): number {
    return userDtls.id;
  }

  compareUserDtls(o1: Pick<IUserDtls, 'id'> | null, o2: Pick<IUserDtls, 'id'> | null): boolean {
    return o1 && o2 ? this.getUserDtlsIdentifier(o1) === this.getUserDtlsIdentifier(o2) : o1 === o2;
  }

  addUserDtlsToCollectionIfMissing<Type extends Pick<IUserDtls, 'id'>>(
    userDtlsCollection: Type[],
    ...userDtlsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const userDtls: Type[] = userDtlsToCheck.filter(isPresent);
    if (userDtls.length > 0) {
      const userDtlsCollectionIdentifiers = userDtlsCollection.map(userDtlsItem => this.getUserDtlsIdentifier(userDtlsItem)!);
      const userDtlsToAdd = userDtls.filter(userDtlsItem => {
        const userDtlsIdentifier = this.getUserDtlsIdentifier(userDtlsItem);
        if (userDtlsCollectionIdentifiers.includes(userDtlsIdentifier)) {
          return false;
        }
        userDtlsCollectionIdentifiers.push(userDtlsIdentifier);
        return true;
      });
      return [...userDtlsToAdd, ...userDtlsCollection];
    }
    return userDtlsCollection;
  }
}
