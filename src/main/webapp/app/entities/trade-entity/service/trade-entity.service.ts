import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITradeEntity, NewTradeEntity } from '../trade-entity.model';

export type PartialUpdateTradeEntity = Partial<ITradeEntity> & Pick<ITradeEntity, 'id'>;

export type EntityResponseType = HttpResponse<ITradeEntity>;
export type EntityArrayResponseType = HttpResponse<ITradeEntity[]>;

@Injectable({ providedIn: 'root' })
export class TradeEntityService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/trade-entities');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(tradeEntity: NewTradeEntity): Observable<EntityResponseType> {
    return this.http.post<ITradeEntity>(this.resourceUrl, tradeEntity, { observe: 'response' });
  }

  update(tradeEntity: ITradeEntity): Observable<EntityResponseType> {
    return this.http.put<ITradeEntity>(`${this.resourceUrl}/${this.getTradeEntityIdentifier(tradeEntity)}`, tradeEntity, {
      observe: 'response',
    });
  }

  partialUpdate(tradeEntity: PartialUpdateTradeEntity): Observable<EntityResponseType> {
    return this.http.patch<ITradeEntity>(`${this.resourceUrl}/${this.getTradeEntityIdentifier(tradeEntity)}`, tradeEntity, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITradeEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITradeEntity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTradeEntityIdentifier(tradeEntity: Pick<ITradeEntity, 'id'>): number {
    return tradeEntity.id;
  }

  compareTradeEntity(o1: Pick<ITradeEntity, 'id'> | null, o2: Pick<ITradeEntity, 'id'> | null): boolean {
    return o1 && o2 ? this.getTradeEntityIdentifier(o1) === this.getTradeEntityIdentifier(o2) : o1 === o2;
  }

  addTradeEntityToCollectionIfMissing<Type extends Pick<ITradeEntity, 'id'>>(
    tradeEntityCollection: Type[],
    ...tradeEntitiesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const tradeEntities: Type[] = tradeEntitiesToCheck.filter(isPresent);
    if (tradeEntities.length > 0) {
      const tradeEntityCollectionIdentifiers = tradeEntityCollection.map(
        tradeEntityItem => this.getTradeEntityIdentifier(tradeEntityItem)!,
      );
      const tradeEntitiesToAdd = tradeEntities.filter(tradeEntityItem => {
        const tradeEntityIdentifier = this.getTradeEntityIdentifier(tradeEntityItem);
        if (tradeEntityCollectionIdentifiers.includes(tradeEntityIdentifier)) {
          return false;
        }
        tradeEntityCollectionIdentifiers.push(tradeEntityIdentifier);
        return true;
      });
      return [...tradeEntitiesToAdd, ...tradeEntityCollection];
    }
    return tradeEntityCollection;
  }
}
