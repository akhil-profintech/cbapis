import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IClientCodes } from '../client-codes.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../client-codes.test-samples';

import { ClientCodesService } from './client-codes.service';

const requireRestSample: IClientCodes = {
  ...sampleWithRequiredData,
};

describe('ClientCodes Service', () => {
  let service: ClientCodesService;
  let httpMock: HttpTestingController;
  let expectedResult: IClientCodes | IClientCodes[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ClientCodesService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a ClientCodes', () => {
      const clientCodes = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(clientCodes).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ClientCodes', () => {
      const clientCodes = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(clientCodes).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ClientCodes', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ClientCodes', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ClientCodes', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addClientCodesToCollectionIfMissing', () => {
      it('should add a ClientCodes to an empty array', () => {
        const clientCodes: IClientCodes = sampleWithRequiredData;
        expectedResult = service.addClientCodesToCollectionIfMissing([], clientCodes);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientCodes);
      });

      it('should not add a ClientCodes to an array that contains it', () => {
        const clientCodes: IClientCodes = sampleWithRequiredData;
        const clientCodesCollection: IClientCodes[] = [
          {
            ...clientCodes,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addClientCodesToCollectionIfMissing(clientCodesCollection, clientCodes);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ClientCodes to an array that doesn't contain it", () => {
        const clientCodes: IClientCodes = sampleWithRequiredData;
        const clientCodesCollection: IClientCodes[] = [sampleWithPartialData];
        expectedResult = service.addClientCodesToCollectionIfMissing(clientCodesCollection, clientCodes);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientCodes);
      });

      it('should add only unique ClientCodes to an array', () => {
        const clientCodesArray: IClientCodes[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const clientCodesCollection: IClientCodes[] = [sampleWithRequiredData];
        expectedResult = service.addClientCodesToCollectionIfMissing(clientCodesCollection, ...clientCodesArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const clientCodes: IClientCodes = sampleWithRequiredData;
        const clientCodes2: IClientCodes = sampleWithPartialData;
        expectedResult = service.addClientCodesToCollectionIfMissing([], clientCodes, clientCodes2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientCodes);
        expect(expectedResult).toContain(clientCodes2);
      });

      it('should accept null and undefined values', () => {
        const clientCodes: IClientCodes = sampleWithRequiredData;
        expectedResult = service.addClientCodesToCollectionIfMissing([], null, clientCodes, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientCodes);
      });

      it('should return initial array if no ClientCodes is added', () => {
        const clientCodesCollection: IClientCodes[] = [sampleWithRequiredData];
        expectedResult = service.addClientCodesToCollectionIfMissing(clientCodesCollection, undefined, null);
        expect(expectedResult).toEqual(clientCodesCollection);
      });
    });

    describe('compareClientCodes', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareClientCodes(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareClientCodes(entity1, entity2);
        const compareResult2 = service.compareClientCodes(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareClientCodes(entity1, entity2);
        const compareResult2 = service.compareClientCodes(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareClientCodes(entity1, entity2);
        const compareResult2 = service.compareClientCodes(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
