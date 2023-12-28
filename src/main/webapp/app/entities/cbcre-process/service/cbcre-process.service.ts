import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICBCREProcess, NewCBCREProcess } from '../cbcre-process.model';

export type PartialUpdateCBCREProcess = Partial<ICBCREProcess> & Pick<ICBCREProcess, 'id'>;

export type EntityResponseType = HttpResponse<ICBCREProcess>;
export type EntityArrayResponseType = HttpResponse<ICBCREProcess[]>;

@Injectable({ providedIn: 'root' })
export class CBCREProcessService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cbcre-processes');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(cBCREProcess: NewCBCREProcess): Observable<EntityResponseType> {
    return this.http.post<ICBCREProcess>(this.resourceUrl, cBCREProcess, { observe: 'response' });
  }

  update(cBCREProcess: ICBCREProcess): Observable<EntityResponseType> {
    return this.http.put<ICBCREProcess>(`${this.resourceUrl}/${this.getCBCREProcessIdentifier(cBCREProcess)}`, cBCREProcess, {
      observe: 'response',
    });
  }

  partialUpdate(cBCREProcess: PartialUpdateCBCREProcess): Observable<EntityResponseType> {
    return this.http.patch<ICBCREProcess>(`${this.resourceUrl}/${this.getCBCREProcessIdentifier(cBCREProcess)}`, cBCREProcess, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICBCREProcess>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICBCREProcess[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCBCREProcessIdentifier(cBCREProcess: Pick<ICBCREProcess, 'id'>): number {
    return cBCREProcess.id;
  }

  compareCBCREProcess(o1: Pick<ICBCREProcess, 'id'> | null, o2: Pick<ICBCREProcess, 'id'> | null): boolean {
    return o1 && o2 ? this.getCBCREProcessIdentifier(o1) === this.getCBCREProcessIdentifier(o2) : o1 === o2;
  }

  addCBCREProcessToCollectionIfMissing<Type extends Pick<ICBCREProcess, 'id'>>(
    cBCREProcessCollection: Type[],
    ...cBCREProcessesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const cBCREProcesses: Type[] = cBCREProcessesToCheck.filter(isPresent);
    if (cBCREProcesses.length > 0) {
      const cBCREProcessCollectionIdentifiers = cBCREProcessCollection.map(
        cBCREProcessItem => this.getCBCREProcessIdentifier(cBCREProcessItem)!,
      );
      const cBCREProcessesToAdd = cBCREProcesses.filter(cBCREProcessItem => {
        const cBCREProcessIdentifier = this.getCBCREProcessIdentifier(cBCREProcessItem);
        if (cBCREProcessCollectionIdentifiers.includes(cBCREProcessIdentifier)) {
          return false;
        }
        cBCREProcessCollectionIdentifiers.push(cBCREProcessIdentifier);
        return true;
      });
      return [...cBCREProcessesToAdd, ...cBCREProcessCollection];
    }
    return cBCREProcessCollection;
  }
}
