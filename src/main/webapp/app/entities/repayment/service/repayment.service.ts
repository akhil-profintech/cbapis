import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRepayment, NewRepayment } from '../repayment.model';

export type PartialUpdateRepayment = Partial<IRepayment> & Pick<IRepayment, 'id'>;

type RestOf<T extends IRepayment | NewRepayment> = Omit<T, 'offerAcceptedDate' | 'repaymentDate' | 'repaymentDueDate'> & {
  offerAcceptedDate?: string | null;
  repaymentDate?: string | null;
  repaymentDueDate?: string | null;
};

export type RestRepayment = RestOf<IRepayment>;

export type NewRestRepayment = RestOf<NewRepayment>;

export type PartialUpdateRestRepayment = RestOf<PartialUpdateRepayment>;

export type EntityResponseType = HttpResponse<IRepayment>;
export type EntityArrayResponseType = HttpResponse<IRepayment[]>;

@Injectable({ providedIn: 'root' })
export class RepaymentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/repayments');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(repayment: NewRepayment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(repayment);
    return this.http
      .post<RestRepayment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(repayment: IRepayment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(repayment);
    return this.http
      .put<RestRepayment>(`${this.resourceUrl}/${this.getRepaymentIdentifier(repayment)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(repayment: PartialUpdateRepayment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(repayment);
    return this.http
      .patch<RestRepayment>(`${this.resourceUrl}/${this.getRepaymentIdentifier(repayment)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestRepayment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestRepayment[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRepaymentIdentifier(repayment: Pick<IRepayment, 'id'>): number {
    return repayment.id;
  }

  compareRepayment(o1: Pick<IRepayment, 'id'> | null, o2: Pick<IRepayment, 'id'> | null): boolean {
    return o1 && o2 ? this.getRepaymentIdentifier(o1) === this.getRepaymentIdentifier(o2) : o1 === o2;
  }

  addRepaymentToCollectionIfMissing<Type extends Pick<IRepayment, 'id'>>(
    repaymentCollection: Type[],
    ...repaymentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const repayments: Type[] = repaymentsToCheck.filter(isPresent);
    if (repayments.length > 0) {
      const repaymentCollectionIdentifiers = repaymentCollection.map(repaymentItem => this.getRepaymentIdentifier(repaymentItem)!);
      const repaymentsToAdd = repayments.filter(repaymentItem => {
        const repaymentIdentifier = this.getRepaymentIdentifier(repaymentItem);
        if (repaymentCollectionIdentifiers.includes(repaymentIdentifier)) {
          return false;
        }
        repaymentCollectionIdentifiers.push(repaymentIdentifier);
        return true;
      });
      return [...repaymentsToAdd, ...repaymentCollection];
    }
    return repaymentCollection;
  }

  protected convertDateFromClient<T extends IRepayment | NewRepayment | PartialUpdateRepayment>(repayment: T): RestOf<T> {
    return {
      ...repayment,
      offerAcceptedDate: repayment.offerAcceptedDate?.format(DATE_FORMAT) ?? null,
      repaymentDate: repayment.repaymentDate?.format(DATE_FORMAT) ?? null,
      repaymentDueDate: repayment.repaymentDueDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restRepayment: RestRepayment): IRepayment {
    return {
      ...restRepayment,
      offerAcceptedDate: restRepayment.offerAcceptedDate ? dayjs(restRepayment.offerAcceptedDate) : undefined,
      repaymentDate: restRepayment.repaymentDate ? dayjs(restRepayment.repaymentDate) : undefined,
      repaymentDueDate: restRepayment.repaymentDueDate ? dayjs(restRepayment.repaymentDueDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestRepayment>): HttpResponse<IRepayment> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestRepayment[]>): HttpResponse<IRepayment[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
