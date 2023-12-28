import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITradePartner, NewTradePartner } from '../trade-partner.model';

export type PartialUpdateTradePartner = Partial<ITradePartner> & Pick<ITradePartner, 'id'>;

export type EntityResponseType = HttpResponse<ITradePartner>;
export type EntityArrayResponseType = HttpResponse<ITradePartner[]>;

@Injectable({ providedIn: 'root' })
export class TradePartnerService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/trade-partners');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(tradePartner: NewTradePartner): Observable<EntityResponseType> {
    return this.http.post<ITradePartner>(this.resourceUrl, tradePartner, { observe: 'response' });
  }

  update(tradePartner: ITradePartner): Observable<EntityResponseType> {
    return this.http.put<ITradePartner>(`${this.resourceUrl}/${this.getTradePartnerIdentifier(tradePartner)}`, tradePartner, {
      observe: 'response',
    });
  }

  partialUpdate(tradePartner: PartialUpdateTradePartner): Observable<EntityResponseType> {
    return this.http.patch<ITradePartner>(`${this.resourceUrl}/${this.getTradePartnerIdentifier(tradePartner)}`, tradePartner, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITradePartner>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITradePartner[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTradePartnerIdentifier(tradePartner: Pick<ITradePartner, 'id'>): number {
    return tradePartner.id;
  }

  compareTradePartner(o1: Pick<ITradePartner, 'id'> | null, o2: Pick<ITradePartner, 'id'> | null): boolean {
    return o1 && o2 ? this.getTradePartnerIdentifier(o1) === this.getTradePartnerIdentifier(o2) : o1 === o2;
  }

  addTradePartnerToCollectionIfMissing<Type extends Pick<ITradePartner, 'id'>>(
    tradePartnerCollection: Type[],
    ...tradePartnersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tradePartners: Type[] = tradePartnersToCheck.filter(isPresent);
    if (tradePartners.length > 0) {
      const tradePartnerCollectionIdentifiers = tradePartnerCollection.map(
        tradePartnerItem => this.getTradePartnerIdentifier(tradePartnerItem)!,
      );
      const tradePartnersToAdd = tradePartners.filter(tradePartnerItem => {
        const tradePartnerIdentifier = this.getTradePartnerIdentifier(tradePartnerItem);
        if (tradePartnerCollectionIdentifiers.includes(tradePartnerIdentifier)) {
          return false;
        }
        tradePartnerCollectionIdentifiers.push(tradePartnerIdentifier);
        return true;
      });
      return [...tradePartnersToAdd, ...tradePartnerCollection];
    }
    return tradePartnerCollection;
  }
}
