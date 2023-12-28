import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CollectionTransactionDetailsDetailComponent } from './collection-transaction-details-detail.component';

describe('CollectionTransactionDetails Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollectionTransactionDetailsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CollectionTransactionDetailsDetailComponent,
              resolve: { collectionTransactionDetails: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CollectionTransactionDetailsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load collectionTransactionDetails on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CollectionTransactionDetailsDetailComponent);

      // THEN
      expect(instance.collectionTransactionDetails).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
