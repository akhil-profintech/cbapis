import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAnchorTraderPartner, NewAnchorTraderPartner } from '../anchor-trader-partner.model';

export type PartialUpdateAnchorTraderPartner = Partial<IAnchorTraderPartner> & Pick<IAnchorTraderPartner, 'id'>;

export type EntityResponseType = HttpResponse<IAnchorTraderPartner>;
export type EntityArrayResponseType = HttpResponse<IAnchorTraderPartner[]>;

@Injectable({ providedIn: 'root' })
export class AnchorTraderPartnerService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/anchor-trader-partners');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(anchorTraderPartner: NewAnchorTraderPartner): Observable<EntityResponseType> {
    return this.http.post<IAnchorTraderPartner>(this.resourceUrl, anchorTraderPartner, { observe: 'response' });
  }

  update(anchorTraderPartner: IAnchorTraderPartner): Observable<EntityResponseType> {
    return this.http.put<IAnchorTraderPartner>(
      `${this.resourceUrl}/${this.getAnchorTraderPartnerIdentifier(anchorTraderPartner)}`,
      anchorTraderPartner,
      { observe: 'response' },
    );
  }

  partialUpdate(anchorTraderPartner: PartialUpdateAnchorTraderPartner): Observable<EntityResponseType> {
    return this.http.patch<IAnchorTraderPartner>(
      `${this.resourceUrl}/${this.getAnchorTraderPartnerIdentifier(anchorTraderPartner)}`,
      anchorTraderPartner,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnchorTraderPartner>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnchorTraderPartner[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAnchorTraderPartnerIdentifier(anchorTraderPartner: Pick<IAnchorTraderPartner, 'id'>): number {
    return anchorTraderPartner.id;
  }

  compareAnchorTraderPartner(o1: Pick<IAnchorTraderPartner, 'id'> | null, o2: Pick<IAnchorTraderPartner, 'id'> | null): boolean {
    return o1 && o2 ? this.getAnchorTraderPartnerIdentifier(o1) === this.getAnchorTraderPartnerIdentifier(o2) : o1 === o2;
  }

  addAnchorTraderPartnerToCollectionIfMissing<Type extends Pick<IAnchorTraderPartner, 'id'>>(
    anchorTraderPartnerCollection: Type[],
    ...anchorTraderPartnersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const anchorTraderPartners: Type[] = anchorTraderPartnersToCheck.filter(isPresent);
    if (anchorTraderPartners.length > 0) {
      const anchorTraderPartnerCollectionIdentifiers = anchorTraderPartnerCollection.map(
        anchorTraderPartnerItem => this.getAnchorTraderPartnerIdentifier(anchorTraderPartnerItem)!,
      );
      const anchorTraderPartnersToAdd = anchorTraderPartners.filter(anchorTraderPartnerItem => {
        const anchorTraderPartnerIdentifier = this.getAnchorTraderPartnerIdentifier(anchorTraderPartnerItem);
        if (anchorTraderPartnerCollectionIdentifiers.includes(anchorTraderPartnerIdentifier)) {
          return false;
        }
        anchorTraderPartnerCollectionIdentifiers.push(anchorTraderPartnerIdentifier);
        return true;
      });
      return [...anchorTraderPartnersToAdd, ...anchorTraderPartnerCollection];
    }
    return anchorTraderPartnerCollection;
  }
}
