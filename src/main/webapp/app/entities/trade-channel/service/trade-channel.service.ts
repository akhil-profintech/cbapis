import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITradeChannel, NewTradeChannel } from '../trade-channel.model';

export type PartialUpdateTradeChannel = Partial<ITradeChannel> & Pick<ITradeChannel, 'id'>;

export type EntityResponseType = HttpResponse<ITradeChannel>;
export type EntityArrayResponseType = HttpResponse<ITradeChannel[]>;

@Injectable({ providedIn: 'root' })
export class TradeChannelService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/trade-channels');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(tradeChannel: NewTradeChannel): Observable<EntityResponseType> {
    return this.http.post<ITradeChannel>(this.resourceUrl, tradeChannel, { observe: 'response' });
  }

  update(tradeChannel: ITradeChannel): Observable<EntityResponseType> {
    return this.http.put<ITradeChannel>(`${this.resourceUrl}/${this.getTradeChannelIdentifier(tradeChannel)}`, tradeChannel, {
      observe: 'response',
    });
  }

  partialUpdate(tradeChannel: PartialUpdateTradeChannel): Observable<EntityResponseType> {
    return this.http.patch<ITradeChannel>(`${this.resourceUrl}/${this.getTradeChannelIdentifier(tradeChannel)}`, tradeChannel, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITradeChannel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITradeChannel[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTradeChannelIdentifier(tradeChannel: Pick<ITradeChannel, 'id'>): number {
    return tradeChannel.id;
  }

  compareTradeChannel(o1: Pick<ITradeChannel, 'id'> | null, o2: Pick<ITradeChannel, 'id'> | null): boolean {
    return o1 && o2 ? this.getTradeChannelIdentifier(o1) === this.getTradeChannelIdentifier(o2) : o1 === o2;
  }

  addTradeChannelToCollectionIfMissing<Type extends Pick<ITradeChannel, 'id'>>(
    tradeChannelCollection: Type[],
    ...tradeChannelsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tradeChannels: Type[] = tradeChannelsToCheck.filter(isPresent);
    if (tradeChannels.length > 0) {
      const tradeChannelCollectionIdentifiers = tradeChannelCollection.map(
        tradeChannelItem => this.getTradeChannelIdentifier(tradeChannelItem)!,
      );
      const tradeChannelsToAdd = tradeChannels.filter(tradeChannelItem => {
        const tradeChannelIdentifier = this.getTradeChannelIdentifier(tradeChannelItem);
        if (tradeChannelCollectionIdentifiers.includes(tradeChannelIdentifier)) {
          return false;
        }
        tradeChannelCollectionIdentifiers.push(tradeChannelIdentifier);
        return true;
      });
      return [...tradeChannelsToAdd, ...tradeChannelCollection];
    }
    return tradeChannelCollection;
  }
}
