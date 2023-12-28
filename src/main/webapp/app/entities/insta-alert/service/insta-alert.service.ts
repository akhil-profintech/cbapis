import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IInstaAlert, NewInstaAlert } from '../insta-alert.model';

export type PartialUpdateInstaAlert = Partial<IInstaAlert> & Pick<IInstaAlert, 'id'>;

export type EntityResponseType = HttpResponse<IInstaAlert>;
export type EntityArrayResponseType = HttpResponse<IInstaAlert[]>;

@Injectable({ providedIn: 'root' })
export class InstaAlertService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/insta-alerts');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(instaAlert: NewInstaAlert): Observable<EntityResponseType> {
    return this.http.post<IInstaAlert>(this.resourceUrl, instaAlert, { observe: 'response' });
  }

  update(instaAlert: IInstaAlert): Observable<EntityResponseType> {
    return this.http.put<IInstaAlert>(`${this.resourceUrl}/${this.getInstaAlertIdentifier(instaAlert)}`, instaAlert, {
      observe: 'response',
    });
  }

  partialUpdate(instaAlert: PartialUpdateInstaAlert): Observable<EntityResponseType> {
    return this.http.patch<IInstaAlert>(`${this.resourceUrl}/${this.getInstaAlertIdentifier(instaAlert)}`, instaAlert, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInstaAlert>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInstaAlert[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getInstaAlertIdentifier(instaAlert: Pick<IInstaAlert, 'id'>): number {
    return instaAlert.id;
  }

  compareInstaAlert(o1: Pick<IInstaAlert, 'id'> | null, o2: Pick<IInstaAlert, 'id'> | null): boolean {
    return o1 && o2 ? this.getInstaAlertIdentifier(o1) === this.getInstaAlertIdentifier(o2) : o1 === o2;
  }

  addInstaAlertToCollectionIfMissing<Type extends Pick<IInstaAlert, 'id'>>(
    instaAlertCollection: Type[],
    ...instaAlertsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const instaAlerts: Type[] = instaAlertsToCheck.filter(isPresent);
    if (instaAlerts.length > 0) {
      const instaAlertCollectionIdentifiers = instaAlertCollection.map(instaAlertItem => this.getInstaAlertIdentifier(instaAlertItem)!);
      const instaAlertsToAdd = instaAlerts.filter(instaAlertItem => {
        const instaAlertIdentifier = this.getInstaAlertIdentifier(instaAlertItem);
        if (instaAlertCollectionIdentifiers.includes(instaAlertIdentifier)) {
          return false;
        }
        instaAlertCollectionIdentifiers.push(instaAlertIdentifier);
        return true;
      });
      return [...instaAlertsToAdd, ...instaAlertCollection];
    }
    return instaAlertCollection;
  }
}
