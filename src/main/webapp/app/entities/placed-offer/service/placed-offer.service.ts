import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPlacedOffer, NewPlacedOffer } from '../placed-offer.model';

export type PartialUpdatePlacedOffer = Partial<IPlacedOffer> & Pick<IPlacedOffer, 'id'>;

type RestOf<T extends IPlacedOffer | NewPlacedOffer> = Omit<T, 'offerDate' | 'placedOfferDate'> & {
  offerDate?: string | null;
  placedOfferDate?: string | null;
};

export type RestPlacedOffer = RestOf<IPlacedOffer>;

export type NewRestPlacedOffer = RestOf<NewPlacedOffer>;

export type PartialUpdateRestPlacedOffer = RestOf<PartialUpdatePlacedOffer>;

export type EntityResponseType = HttpResponse<IPlacedOffer>;
export type EntityArrayResponseType = HttpResponse<IPlacedOffer[]>;

@Injectable({ providedIn: 'root' })
export class PlacedOfferService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/placed-offers');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(placedOffer: NewPlacedOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(placedOffer);
    return this.http
      .post<RestPlacedOffer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(placedOffer: IPlacedOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(placedOffer);
    return this.http
      .put<RestPlacedOffer>(`${this.resourceUrl}/${this.getPlacedOfferIdentifier(placedOffer)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(placedOffer: PartialUpdatePlacedOffer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(placedOffer);
    return this.http
      .patch<RestPlacedOffer>(`${this.resourceUrl}/${this.getPlacedOfferIdentifier(placedOffer)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestPlacedOffer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestPlacedOffer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPlacedOfferIdentifier(placedOffer: Pick<IPlacedOffer, 'id'>): number {
    return placedOffer.id;
  }

  comparePlacedOffer(o1: Pick<IPlacedOffer, 'id'> | null, o2: Pick<IPlacedOffer, 'id'> | null): boolean {
    return o1 && o2 ? this.getPlacedOfferIdentifier(o1) === this.getPlacedOfferIdentifier(o2) : o1 === o2;
  }

  addPlacedOfferToCollectionIfMissing<Type extends Pick<IPlacedOffer, 'id'>>(
    placedOfferCollection: Type[],
    ...placedOffersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const placedOffers: Type[] = placedOffersToCheck.filter(isPresent);
    if (placedOffers.length > 0) {
      const placedOfferCollectionIdentifiers = placedOfferCollection.map(
        placedOfferItem => this.getPlacedOfferIdentifier(placedOfferItem)!,
      );
      const placedOffersToAdd = placedOffers.filter(placedOfferItem => {
        const placedOfferIdentifier = this.getPlacedOfferIdentifier(placedOfferItem);
        if (placedOfferCollectionIdentifiers.includes(placedOfferIdentifier)) {
          return false;
        }
        placedOfferCollectionIdentifiers.push(placedOfferIdentifier);
        return true;
      });
      return [...placedOffersToAdd, ...placedOfferCollection];
    }
    return placedOfferCollection;
  }

  protected convertDateFromClient<T extends IPlacedOffer | NewPlacedOffer | PartialUpdatePlacedOffer>(placedOffer: T): RestOf<T> {
    return {
      ...placedOffer,
      offerDate: placedOffer.offerDate?.format(DATE_FORMAT) ?? null,
      placedOfferDate: placedOffer.placedOfferDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restPlacedOffer: RestPlacedOffer): IPlacedOffer {
    return {
      ...restPlacedOffer,
      offerDate: restPlacedOffer.offerDate ? dayjs(restPlacedOffer.offerDate) : undefined,
      placedOfferDate: restPlacedOffer.placedOfferDate ? dayjs(restPlacedOffer.placedOfferDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestPlacedOffer>): HttpResponse<IPlacedOffer> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestPlacedOffer[]>): HttpResponse<IPlacedOffer[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
