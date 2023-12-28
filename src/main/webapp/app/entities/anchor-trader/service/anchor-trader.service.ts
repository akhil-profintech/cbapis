import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAnchorTrader, NewAnchorTrader } from '../anchor-trader.model';

export type PartialUpdateAnchorTrader = Partial<IAnchorTrader> & Pick<IAnchorTrader, 'id'>;

export type EntityResponseType = HttpResponse<IAnchorTrader>;
export type EntityArrayResponseType = HttpResponse<IAnchorTrader[]>;

@Injectable({ providedIn: 'root' })
export class AnchorTraderService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/anchor-traders');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(anchorTrader: NewAnchorTrader): Observable<EntityResponseType> {
    return this.http.post<IAnchorTrader>(this.resourceUrl, anchorTrader, { observe: 'response' });
  }

  update(anchorTrader: IAnchorTrader): Observable<EntityResponseType> {
    return this.http.put<IAnchorTrader>(`${this.resourceUrl}/${this.getAnchorTraderIdentifier(anchorTrader)}`, anchorTrader, {
      observe: 'response',
    });
  }

  partialUpdate(anchorTrader: PartialUpdateAnchorTrader): Observable<EntityResponseType> {
    return this.http.patch<IAnchorTrader>(`${this.resourceUrl}/${this.getAnchorTraderIdentifier(anchorTrader)}`, anchorTrader, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnchorTrader>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnchorTrader[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAnchorTraderIdentifier(anchorTrader: Pick<IAnchorTrader, 'id'>): number {
    return anchorTrader.id;
  }

  compareAnchorTrader(o1: Pick<IAnchorTrader, 'id'> | null, o2: Pick<IAnchorTrader, 'id'> | null): boolean {
    return o1 && o2 ? this.getAnchorTraderIdentifier(o1) === this.getAnchorTraderIdentifier(o2) : o1 === o2;
  }

  addAnchorTraderToCollectionIfMissing<Type extends Pick<IAnchorTrader, 'id'>>(
    anchorTraderCollection: Type[],
    ...anchorTradersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const anchorTraders: Type[] = anchorTradersToCheck.filter(isPresent);
    if (anchorTraders.length > 0) {
      const anchorTraderCollectionIdentifiers = anchorTraderCollection.map(
        anchorTraderItem => this.getAnchorTraderIdentifier(anchorTraderItem)!,
      );
      const anchorTradersToAdd = anchorTraders.filter(anchorTraderItem => {
        const anchorTraderIdentifier = this.getAnchorTraderIdentifier(anchorTraderItem);
        if (anchorTraderCollectionIdentifiers.includes(anchorTraderIdentifier)) {
          return false;
        }
        anchorTraderCollectionIdentifiers.push(anchorTraderIdentifier);
        return true;
      });
      return [...anchorTradersToAdd, ...anchorTraderCollection];
    }
    return anchorTraderCollection;
  }
}
